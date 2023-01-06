package com.example.demo.relatorio.service;

import com.example.demo.despesa.entity.Despesa;
import com.example.demo.despesa.repository.DespesaRepository;
import com.example.demo.fortune.util.MonetarioUtil;
import com.example.demo.fortune.util.enuns.TipoMoeda;
import com.example.demo.investimento.entity.Investimento;
import com.example.demo.investimento.repository.InvestimentoRepository;
import com.example.demo.receita.entity.Receita;
import com.example.demo.receita.repository.ReceitaRepository;
import com.example.demo.relatorio.dto.FiltroRelatorioDTO;
import com.example.demo.relatorio.dto.RelatorioDTO;
import com.example.demo.usuario.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
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
