package com.example.demo.model.bo;

import com.example.demo.model.bo.exceptionhandler.PessoaInexistenteOuInativaException;
import com.example.demo.model.dao.investimento.InvestimentoDAO;
import com.example.demo.model.dto.investimento.InvestimentoDTO;
import com.example.demo.model.dto.investimento.ListaInvestimentoTipoDTO;
import com.example.demo.model.entity.Investimento;
import com.example.demo.model.util.ModelMapperUtil;
import com.example.demo.model.util.MonetarioUtil;
import com.example.demo.model.util.enuns.TipoInvestimentos;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvestimentoBO {
    @Autowired
    private InvestimentoDAO investimentoDAO;

    MonetarioUtil monetarioUtil = MonetarioUtil.getInstance();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private ModelMapper modelMapper = ModelMapperUtil.getInstance();

    public List<InvestimentoDTO> buscaDespesaAll(){
        List<Investimento> despesas = investimentoDAO.findAll();

        return despesas.stream()
                .map(task -> modelMapper.map(task, InvestimentoDTO.class))
                .collect(Collectors.toList());
    }

    public ListaInvestimentoTipoDTO buscaDespesasTipo(){
        List<InvestimentoDTO> fixas = new ArrayList<>();
        List<InvestimentoDTO> variaveis = new ArrayList<>();
        List<InvestimentoDTO> extraordinarias = new ArrayList<>();
        List<InvestimentoDTO> regulares = new ArrayList<>();

        List<InvestimentoDTO> investimentos = investimentoDAO.findAll().stream()
                .map(inv -> {
                    InvestimentoDTO invDto = modelMapper.map(inv, InvestimentoDTO.class);
                    invDto.setValorFormatado(monetarioUtil.monetarios(inv.getValor(), 17, inv.getTipomoeda()));
                    invDto.setDataFormatada(invDto.getDataInvestimento().format(formatter));
                    return invDto;
                }).collect(Collectors.toList());

        for (InvestimentoDTO investimento: investimentos) {
            if (investimento.getInvestimento().equals(TipoInvestimentos.TESOURO_DIRETO))
                fixas.add(investimento);
            if (investimento.getInvestimento().equals(TipoInvestimentos.CDB))
                variaveis.add(investimento);
            if (investimento.getInvestimento().equals(TipoInvestimentos.ACOES_DE_EMPRESAS))
                extraordinarias.add(investimento);
            if (investimento.getInvestimento().equals(TipoInvestimentos.FUNDOS_IMOBILIARIOS))
                regulares.add(investimento);
        }

        return ListaInvestimentoTipoDTO.builder()
                .despesasFixas(fixas)
                .despesasVariaveis(variaveis)
                .despesasExtraordinarias(extraordinarias)
                .despesasRegulares(regulares)
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
}
