package com.example.demo.fortune.controller;

import com.example.demo.conversor.dto.ConversorDTO;
import com.example.demo.despesa.dto.ListaDespesaTipoDTO;
import com.example.demo.despesa.service.DespesaService;
import com.example.demo.fortune.util.MonetarioUtil;
import com.example.demo.fortune.util.TotalizadorMoedaDTO;
import com.example.demo.fortune.util.cotacao.dto.CotacaoDTO;
import com.example.demo.fortune.util.cotacao.factory.CotacaoFabrica;
import com.example.demo.fortune.util.cotacao.template_method.CotacaoAPI;
import com.example.demo.investimento.dto.ListaInvestimentoTipoDTO;
import com.example.demo.investimento.service.InvestimentoService;
import com.example.demo.perfil.dto.PerfilDTO;
import com.example.demo.perfil.service.PerfilService;
import com.example.demo.receita.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
public class MainController {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private InvestimentoService investimentoService;

    @Autowired
    private DespesaService depesaService;

    @Autowired
    private PerfilService perfilService;

    @GetMapping("/receita")
    public String receita(ModelMap model) {
        TotalizadorMoedaDTO receitas = receitaService.buscaReceitasAll();
        model.addAttribute("receitas", receitas);
        return "receita";
    }

    @GetMapping("/investimento")
    public String investimento(ModelMap model) {
        ListaInvestimentoTipoDTO investimentos = investimentoService.buscaDespesasTipo();
        model.addAttribute("investimentos", investimentos);
        return "investimento";
    }

    @GetMapping("/despesa")
    public String despesa(ModelMap model) {
        ListaDespesaTipoDTO despesas = depesaService.buscaDespesasTipo();
        model.addAttribute("despesas", despesas);
        return "despesa";
    }

    @GetMapping("/perfis")
    public String perfil(ModelMap model) {
        List<PerfilDTO> funcionarios = perfilService.buscaAll();
        model.addAttribute("perfils", funcionarios);
        return "perfil";
    }

    @GetMapping("/conversor")
    public String conversor(ConversorDTO conversor, Model model) throws IOException {
        MonetarioUtil monetarioUtil = MonetarioUtil.getInstance();

        if (Objects.nonNull(conversor.getMoedaPrimaria()) && Objects.nonNull(conversor.getValor())) {
            CotacaoAPI cotacao = CotacaoFabrica.getCotacao(conversor.getMoedaPrimaria());
            CotacaoDTO valorConvertido = cotacao.consultaCotacao(conversor.getValor());
            valorConvertido.setValorConvertidoFormatado(monetarioUtil.monetarios(valorConvertido.getValorConvertido(), 17, conversor.getMoedaPrimaria()));
            model.addAttribute("conversor", valorConvertido);
        }
        return "conversor";
    }

    @GetMapping("/relatorio")
    public String relatorio() {
        return "relatorio";
    }
}