package com.example.demo.model.investimento.service;

import com.example.demo.model.fortune.exceptions.PessoaInexistenteOuInativaException;
import com.example.demo.model.fortune.exceptions.ResourceNotFoundException;
import com.example.demo.model.fortune.util.DateUtil;
import com.example.demo.model.fortune.util.MonetarioUtil;
import com.example.demo.model.fortune.util.enuns.TipoInvestimentos;
import com.example.demo.model.investimento.dto.InvestimentoDTO;
import com.example.demo.model.investimento.dto.ListaInvestimentoTipoDTO;
import com.example.demo.model.investimento.entity.Investimento;
import com.example.demo.model.investimento.repository.InvestimentoRepository;
import com.example.demo.model.usuario.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InvestimentoService {
    private final InvestimentoRepository investimentoRepository;

    private final UserService userService;

    private final ModelMapper modelMapper;

    public ListaInvestimentoTipoDTO buscaInvestimentosByPerfil() {
        List<InvestimentoDTO> tesouroDireto = new ArrayList<>();
        List<InvestimentoDTO> cdb = new ArrayList<>();
        List<InvestimentoDTO> acoes = new ArrayList<>();
        List<InvestimentoDTO> fundosImobiliarios = new ArrayList<>();

        List<InvestimentoDTO> investimentos = investimentoRepository
                .findInvestimentoByUser(userService.getUser())
                .stream()
                .map(inv -> {
                    InvestimentoDTO invDto = modelMapper.map(inv, InvestimentoDTO.class);
                    invDto.setValorFormatado(MonetarioUtil.getInstance().monetarios(inv.getValor(), 17, inv.getTipomoeda()));
                    invDto.setDataFormatada(invDto.getDataInvestimento().format(DateUtil.FORMATTER));
                    return invDto;
                }).collect(Collectors.toList());

        for (InvestimentoDTO investimento : investimentos) {
            if (investimento.getInvestimento().equals(TipoInvestimentos.TESOURO_DIRETO))
                tesouroDireto.add(investimento);
            if (investimento.getInvestimento().equals(TipoInvestimentos.CDB))
                cdb.add(investimento);
            if (investimento.getInvestimento().equals(TipoInvestimentos.ACOES_DE_EMPRESAS))
                acoes.add(investimento);
            if (investimento.getInvestimento().equals(TipoInvestimentos.FUNDOS_IMOBILIARIOS))
                fundosImobiliarios.add(investimento);
        }

        return ListaInvestimentoTipoDTO.builder()
                .tesouroDireto(tesouroDireto)
                .cdb(cdb)
                .acoes(acoes)
                .fundosImobiliarios(fundosImobiliarios)
                .build();
    }

    public InvestimentoDTO buscaInvestimentoById(Long codigo) {
        Investimento investimento = investimentoRepository.findById(codigo)
                .orElseThrow(() -> new ResourceNotFoundException(codigo));
        InvestimentoDTO investimentoDTO = modelMapper.map(investimento, InvestimentoDTO.class);
        investimentoDTO.setDataFormatada(investimentoDTO.getDataInvestimento().format(DateUtil.FORMATTER));
        investimentoDTO.setValorFormatado(MonetarioUtil.getInstance().monetarios(investimentoDTO.getValor(), 17, investimentoDTO.getTipomoeda()));
        return investimentoDTO;
    }

    public InvestimentoDTO criarInvestimento(InvestimentoDTO investimentoDTO, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
        Investimento investimento = modelMapper.map(investimentoDTO, Investimento.class);
        investimento.setUser(userService.getUser());
        return modelMapper.map(investimentoRepository.save(investimento), InvestimentoDTO.class);
    }

    public void removerInvestimento(Long codigo) {
        investimentoRepository.deleteById(codigo);
    }

    public InvestimentoDTO atualizaInvestimento(Long codigo, InvestimentoDTO investimento) throws PessoaInexistenteOuInativaException {
        Investimento investimentoSalvo = investimentoRepository.getById(codigo);
        BeanUtils.copyProperties(investimento, investimentoSalvo, "codigo");
        investimento = modelMapper.map(investimentoRepository.save(investimentoSalvo), InvestimentoDTO.class);
        investimento.setValorFormatado(MonetarioUtil.getInstance().monetarios(investimento.getValor(), 17, investimento.getTipomoeda()));
        investimento.setDataFormatada(investimento.getDataInvestimento().format(DateUtil.FORMATTER));
        return investimento;
    }
}
