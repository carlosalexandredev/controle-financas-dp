package com.example.demo.investimento.service;

import com.example.demo.fortune.exceptions.PessoaInexistenteOuInativaException;
import com.example.demo.investimento.dto.InvestimentoDTO;
import com.example.demo.investimento.dto.ListaInvestimentoTipoDTO;
import com.example.demo.investimento.entity.Investimento;
import com.example.demo.investimento.repository.InvestimentoRepository;
import com.example.demo.fortune.util.MonetarioUtil;
import com.example.demo.fortune.util.enuns.TipoInvestimentos;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvestimentoService {
    @Autowired
    private InvestimentoRepository investimentoDAO;

    MonetarioUtil monetarioUtil = MonetarioUtil.getInstance();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Autowired
    private ModelMapper modelMapper;

    public List<InvestimentoDTO> buscaDespesaAll(){
        List<Investimento> despesas = investimentoDAO.findAll();

        return despesas.stream()
                .map(task -> modelMapper.map(task, InvestimentoDTO.class))
                .collect(Collectors.toList());
    }

    public ListaInvestimentoTipoDTO buscaDespesasTipo(){
        List<InvestimentoDTO> tesouroDireto = new ArrayList<>();
        List<InvestimentoDTO> cdb = new ArrayList<>();
        List<InvestimentoDTO> acoes = new ArrayList<>();
        List<InvestimentoDTO> fundosImobiliarios = new ArrayList<>();

        List<InvestimentoDTO> investimentos = investimentoDAO.findAll().stream()
                .map(inv -> {
                    InvestimentoDTO invDto = modelMapper.map(inv, InvestimentoDTO.class);
                    invDto.setValorFormatado(monetarioUtil.monetarios(inv.getValor(), 17, inv.getTipomoeda()));
                    invDto.setDataFormatada(invDto.getDataInvestimento().format(formatter));
                    return invDto;
                }).collect(Collectors.toList());

        for (InvestimentoDTO investimento: investimentos) {
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

//    public BigDecimal calculaTotalDespesas(List<DespesaDTO> despesas){
//        return despesas.stream()
//                .map(despesa -> despesa.getValor())
//                .reduce(BigDecimal.ZERO,BigDecimal::add);
//    }

    public InvestimentoDTO buscaInvestimentoById(Long codigo) {
        Optional<Investimento> despesa = investimentoDAO.findById(codigo);
        InvestimentoDTO investimentoDTO = modelMapper.map(despesa.get(), InvestimentoDTO.class);
        investimentoDTO.setDataFormatada(investimentoDTO.getDataInvestimento().format(formatter));
        investimentoDTO.setValorFormatado(monetarioUtil.monetarios(investimentoDTO.getValor(), 17, investimentoDTO.getTipomoeda()));
        return investimentoDTO;
    }

    public InvestimentoDTO criarInvestimento(InvestimentoDTO investimento, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
        Investimento investimentoSalvo = investimentoDAO.save(modelMapper.map(investimento, Investimento.class));
        return modelMapper.map(investimento, InvestimentoDTO.class);
    }

    public void removerInvestimento(Long codigo) {
        investimentoDAO.deleteById(codigo);
    }

    public InvestimentoDTO atualizaInvestimento(Long codigo, InvestimentoDTO investimento) throws PessoaInexistenteOuInativaException {
        Investimento investimentoSalvo = investimentoDAO.getById(codigo);
        BeanUtils.copyProperties(investimento, investimentoSalvo, "codigo");
        investimento = modelMapper.map(investimentoDAO.save(investimentoSalvo), InvestimentoDTO.class);
        investimento.setValorFormatado(monetarioUtil.monetarios(investimento.getValor(), 17, investimento.getTipomoeda()));
        investimento.setDataFormatada(investimento.getDataInvestimento().format(formatter));
        return investimento;
    }
}
