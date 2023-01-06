package com.example.demo.fortune.controller;

import com.example.demo.conversor.dto.ConversorDTO;
import com.example.demo.despesa.dto.ListaDespesaTipoDTO;
import com.example.demo.despesa.service.DespesaService;
import com.example.demo.fortune.exceptions.PessoaInexistenteOuInativaException;
import com.example.demo.fortune.util.MonetarioUtil;
import com.example.demo.fortune.util.TotalizadorMoedaDTO;
import com.example.demo.fortune.util.cotacao.dto.CotacaoDTO;
import com.example.demo.fortune.util.cotacao.factory.CotacaoFabrica;
import com.example.demo.fortune.util.cotacao.template_method.CotacaoAPI;
import com.example.demo.investimento.dto.InvestimentoDTO;
import com.example.demo.investimento.dto.ListaInvestimentoTipoDTO;
import com.example.demo.investimento.service.InvestimentoService;
import com.example.demo.perfil.dto.PerfilDTO;
import com.example.demo.perfil.service.PerfilService;
import com.example.demo.receita.service.ReceitaService;
import com.example.demo.relatorio.dto.FiltroRelatorioDTO;
import com.example.demo.relatorio.dto.RelatorioDTO;
import com.example.demo.relatorio.service.RelatorioService;
import com.example.demo.usuario.entity.User;
import com.example.demo.usuario.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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

    @Autowired RelatorioService rltService;

    @Autowired
    private UserService userService;

    @GetMapping("/receita")
    public String receita(ModelMap model) {
        User user = userService.getUser();
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            TotalizadorMoedaDTO receitas = receitaService.buscaReceitasByPerfil();
            model.addAttribute("receitas", receitas);
            return "receita";
        } else {
            return "login";
        }
    }

    @GetMapping("/investimento")
    public String investimento(ModelMap model) {
        User user = userService.getUser();
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            ListaInvestimentoTipoDTO investimentos = investimentoService.buscaInvestimentosByPerfil();
            model.addAttribute("investimentos", investimentos);
            return "investimento";
        } else {
            return "login";
        }
    }

    @GetMapping("/despesa")
    public String despesa(ModelMap model) {
        User user = userService.getUser();

        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            ListaDespesaTipoDTO despesas = depesaService.buscaDespesasByPerfil();
            model.addAttribute("despesas", despesas);
            return "despesa";
        } else {
            return "login";
        }
    }

    @GetMapping("/perfis")
    public String perfil(ModelMap model) {
        User user = userService.getUser();

        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            List<PerfilDTO> funcionarios = perfilService.buscaAll();
            model.addAttribute("perfils", funcionarios);
            return "perfil";
        } else {
            return "login";
        }
    }

    @GetMapping("/conversor")
    public String conversor(ConversorDTO conversor, Model model) throws IOException {
        MonetarioUtil monetarioUtil = MonetarioUtil.getInstance();
        User user = userService.getUser();
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            if (Objects.nonNull(conversor.getMoedaPrimaria()) && Objects.nonNull(conversor.getValor())) {
                CotacaoAPI cotacao = CotacaoFabrica.getCotacao(conversor.getMoedaPrimaria());
                CotacaoDTO valorConvertido = cotacao.consultaCotacao(conversor.getValor());
                valorConvertido.setValorConvertidoFormatado(monetarioUtil.monetarios(valorConvertido.getValorConvertido(), 17, conversor.getMoedaPrimaria()));
                model.addAttribute("conversor", valorConvertido);
            }
            return "conversor";
        } else {
            return "login";
        }
    }

    @GetMapping("/relatorio")
    public String relatorio(FiltroRelatorioDTO filtroReq, ModelMap model) {
        User user = userService.getUser();

        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            RelatorioDTO totais = rltService.buscaTotais(filtroReq);
            model.addAttribute("totais", totais);
        return "relatorio";
        } else {
            return "login";
        }
    }


}