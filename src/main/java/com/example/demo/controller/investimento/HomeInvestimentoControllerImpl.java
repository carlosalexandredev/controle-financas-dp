package com.example.demo.controller.investimento;

import com.example.demo.model.fortune.exceptions.PessoaInexistenteOuInativaException;
import com.example.demo.model.investimento.dto.InvestimentoDTO;
import com.example.demo.model.investimento.service.InvestimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class HomeInvestimentoControllerImpl implements HomeInvestimentoController {
    @Autowired
    private InvestimentoService investimentoService;

    @Override
    public String salvarInvestimento(@Valid @ModelAttribute InvestimentoDTO investimento, BindingResult result, RedirectAttributes attributes, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique se todos os campos foram preechidos!");
            return "/investimento";
        }
        investimentoService.criarInvestimento(investimento, response);
        return "/investimento";
    }

    @Override
    public String handleDeleteInvestimento(@RequestParam(name = "codigo") Long codigo) {
        investimentoService.removerInvestimento(codigo);

        return "/investimento";
    }

    @Override
    public String editarInvestimento(@PathVariable long codigo, Model model) {
        InvestimentoDTO investimento = investimentoService.buscaInvestimentoById(codigo);
        model.addAttribute("investimento", investimento);
        return "updateinvestimento";
    }

    @Override
    public String updateInvestimento(@PathVariable("codigo") long codigo, @Valid InvestimentoDTO investimento,
                                BindingResult result, Model model, RedirectAttributes attributes, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
        investimento = investimentoService.atualizaInvestimento(codigo, investimento);
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
