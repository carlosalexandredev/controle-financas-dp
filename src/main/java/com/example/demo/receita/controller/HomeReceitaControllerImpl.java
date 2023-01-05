package com.example.demo.receita.controller;

import com.example.demo.fortune.exceptions.PessoaInexistenteOuInativaException;
import com.example.demo.receita.dto.ReceitaDTO;
import com.example.demo.receita.service.ReceitaService;
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
public class HomeReceitaControllerImpl implements HomeReceitaController {
    @Autowired
    private ReceitaService receitaService;

    @Override
    public String salvarReceita(@Valid @ModelAttribute ReceitaDTO receita, BindingResult result, RedirectAttributes attributes, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique se todos os campos foram preechidos!");
            return "redirect:/receita";
        }
        ReceitaDTO receitSalva = receitaService.criarDespesa(receita, response);
        return "redirect:/receita";
    }

    @Override
    public String handleDeleteReceita(@RequestParam(name = "codigo") Long codigo) {
        receitaService.removerReceita(codigo);
        return "redirect:/receita";
    }

    @Override
    public String editarReceita(@PathVariable long codigo, Model model) throws PessoaInexistenteOuInativaException {
        ReceitaDTO receita = receitaService.buscaReceitaById(codigo);
        model.addAttribute("receita", receita);
        return "updatereceita";
    }

    @Override
    public String updateReceita(@PathVariable("codigo") long codigo, @Valid ReceitaDTO receita,
                                BindingResult result, Model model, RedirectAttributes attributes, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
        receitaService.atualizaReceita(codigo, receita);
        if (result.hasErrors()) {
            receita.setCodigo(codigo);
            model.addAttribute("receita", receita);
            attributes.addFlashAttribute("mensagem", "Verifique se todos os campos foram preechidos!");
            return "updatereceita";
        }
        ReceitaDTO receitaDto = receitaService.atualizaReceita(codigo, receita);
        model.addAttribute("receita", receitaDto);
        return "updatereceita";
    }

}
