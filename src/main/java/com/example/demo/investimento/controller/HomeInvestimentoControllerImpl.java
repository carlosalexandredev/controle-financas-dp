package com.example.demo.investimento.controller;

import com.example.demo.fortune.exceptions.PessoaInexistenteOuInativaException;
import com.example.demo.investimento.service.InvestimentoService;
import com.example.demo.investimento.dto.InvestimentoDTO;
import com.example.demo.investimento.dto.ListaInvestimentoTipoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class HomeInvestimentoControllerImpl implements HomeInvestimentoController {
    @Autowired
    private InvestimentoService investimentoBO;

    @Override
    public String investimento(ModelMap model){
        ListaInvestimentoTipoDTO investimentos = investimentoBO.buscaDespesasTipo();
        model.addAttribute("investimentos", investimentos);
        return "investimento";
    }

    @Override
    public String salvarInvestimento(@Valid @ModelAttribute InvestimentoDTO investimento, BindingResult result, RedirectAttributes attributes, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique se todos os campos foram preechidos!");
            return "/investimento";
        }
        investimentoBO.criarInvestimento(investimento, response);
        return "/investimento";
    }

    @Override
    public String handleDeleteInvestimento(@RequestParam(name = "codigo") Long codigo) {
        investimentoBO.removerInvestimento(codigo);

        return "/investimento";
    }

    @Override
    public String editarInvestimento(@PathVariable long codigo, Model model) {
        InvestimentoDTO investimento = investimentoBO.buscaInvestimentoById(codigo);
        model.addAttribute("investimento", investimento);
        return "updateinvestimento";
    }

    @Override
    public String updateInvestimento(@PathVariable("codigo") long codigo, @Valid InvestimentoDTO investimento,
                                BindingResult result, Model model, RedirectAttributes attributes, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
        investimento = investimentoBO.atualizaInvestimento(codigo, investimento);
        if (result.hasErrors()) {
            investimento.setCodigo(codigo);
            model.addAttribute("investimento", investimento);
            attributes.addFlashAttribute("mensagem", "Verifique se todos os campos foram preechidos!");
            return "updateinvestimento";
        }
        model.addAttribute("investimento", investimento);
        return "updateinvestimento";
    }

}
