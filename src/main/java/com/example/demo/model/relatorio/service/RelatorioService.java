package com.example.demo.model.relatorio.service;

import com.example.demo.model.despesa.entity.Despesa;
import com.example.demo.model.despesa.repository.DespesaRepository;
import com.example.demo.model.fortune.util.MonetarioUtil;
import com.example.demo.model.fortune.util.enuns.TipoMoeda;
import com.example.demo.model.investimento.entity.Investimento;
import com.example.demo.model.investimento.repository.InvestimentoRepository;
import com.example.demo.model.receita.entity.Receita;
import com.example.demo.model.receita.repository.ReceitaRepository;
import com.example.demo.model.relatorio.dto.FiltroRelatorioDTO;
import com.example.demo.model.relatorio.dto.RelatorioDTO;
import com.example.demo.model.usuario.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private ReceitaRepository rptReceita;
    @Autowired
    private InvestimentoRepository rptInvestimento;
    @Autowired
    private UserService userService;

    @Autowired
    private DespesaRepository rptDespesa;
    MonetarioUtil monetarioUtil = MonetarioUtil.getInstance();

    public RelatorioDTO buscaTotais(FiltroRelatorioDTO filtro){

        List<Receita> receita = rptReceita.findReceitasByUser(userService.getUser());
        List<Investimento> investimento = rptInvestimento.findInvestimentoByUser(userService.getUser());
        List<Despesa> despesa = rptDespesa.findDespesaByUser(userService.getUser());

        if(filtro.getData() != null){
            receita = receita.stream().filter(r -> r.getData().equals(filtro.getData())).collect(Collectors.toList());
            investimento = investimento.stream().filter(i -> i.getData().equals(filtro.getData())).collect(Collectors.toList());
            despesa = despesa.stream().filter(d -> d.getDataVencimento().equals(filtro.getData())).collect(Collectors.toList());
        }

        return RelatorioDTO.builder()
                .filtro(filtro)
                .receitaReal(calculaTotalReceita(receita, TipoMoeda.REAL))
                .receitaEuro(calculaTotalReceita(receita, TipoMoeda.EURO))
                .receitaDolar(calculaTotalReceita(receita,TipoMoeda.DOLAR))
                .despesaReal(calculaTotalDespesa(despesa, TipoMoeda.REAL))
                .despesaEuro(calculaTotalDespesa(despesa, TipoMoeda.EURO))
                .despesaDolar(calculaTotalDespesa(despesa,TipoMoeda.DOLAR))
                .investimentoReal(calculaTotalInvestimento(investimento, TipoMoeda.REAL))
                .investimentoEuro(calculaTotalInvestimento(investimento, TipoMoeda.EURO))
                .investimentoDolar(calculaTotalInvestimento(investimento,TipoMoeda.DOLAR))
                .totalReal(totalPorMoeda(despesa, receita, investimento, TipoMoeda.REAL))
                .totalEuro(totalPorMoeda(despesa, receita, investimento, TipoMoeda.EURO))
                .totalDolar(totalPorMoeda(despesa, receita, investimento,TipoMoeda.DOLAR))
                .build();
    }

    private String totalPorMoeda(List<Despesa> despesa, List<Receita> receita, List<Investimento> investimento, TipoMoeda moeda){
        BigDecimal totalDespesa = despesa.stream()
                .filter(rec -> rec.getTipomoeda() == moeda)
                .map(rec -> rec.getValor())
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        BigDecimal totalReceita = receita.stream()
                .filter(rec -> rec.getTipomoeda() == moeda)
                .map(rec -> rec.getValor())
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        BigDecimal totalInvestimento = investimento.stream()
                .filter(rec -> rec.getTipomoeda() == moeda)
                .map(rec -> rec.getValor())
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        return monetarioUtil.monetarios(totalReceita.add(totalInvestimento).subtract(totalDespesa), 17, moeda);
    }

    private String calculaTotalDespesa(List<Despesa> despesa, TipoMoeda moeda) {
        BigDecimal total = despesa.stream()
                .filter(rec -> rec.getTipomoeda() == moeda)
                .map(rec -> rec.getValor())
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        return monetarioUtil.monetarios(total, 17, moeda);
    }
    private String calculaTotalReceita(List<Receita> receitas, TipoMoeda moeda) {
        BigDecimal total = receitas.stream()
                .filter(rec -> rec.getTipomoeda() == moeda)
                .map(rec -> rec.getValor())
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        return monetarioUtil.monetarios(total, 17, moeda);
    }

    private String calculaTotalInvestimento(List<Investimento> investimento, TipoMoeda moeda) {
        BigDecimal total = investimento.stream()
                .filter(rec -> rec.getTipomoeda() == moeda)
                .map(rec -> rec.getValor())
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        return monetarioUtil.monetarios(total, 17, moeda);
    }

}
