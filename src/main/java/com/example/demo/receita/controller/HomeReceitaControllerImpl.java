package com.example.demo.receita.controller;

import com.example.demo.fortune.exceptions.PessoaInexistenteOuInativaException;
import com.example.demo.receita.dto.ReceitaDTO;
import com.example.demo.receita.service.ReceitaService;
import com.example.demo.fortune.util.TotalizadorMoedaDTO;
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
public class HomeReceitaControllerImpl implements HomeReceitaController {
    @Autowired
    private ReceitaService receitaBO;

    @Override
    public String receitas(ModelMap model){
        TotalizadorMoedaDTO receitas = receitaBO.buscaReceitasAll();
        model.addAttribute("receitas", receitas);
        return "receita";
    }

    @Override
    public String salvarReceita(@Valid @ModelAttribute ReceitaDTO receita, BindingResult result, RedirectAttributes attributes, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique se todos os campos foram preechidos!");
            return "redirect:/receita";
        }
        ReceitaDTO receitSalva = receitaBO.criarDespesa(receita, response);
        return "redirect:/receita";
    }

    @Override
    public String handleDeleteReceita(@RequestParam(name = "codigo") Long codigo) {
        receitaBO.removerReceita(codigo);
        return "redirect:/receita";
    }

    @Override
    public String editarReceita(@PathVariable long codigo, Model model) throws PessoaInexistenteOuInativaException {
        ReceitaDTO receita = receitaBO.buscaReceitaById(codigo);
        model.addAttribute("receita", receita);
        return "updatereceita";
    }

    @Override
    public String updateReceita(@PathVariable("codigo") long codigo, @Valid ReceitaDTO receita,
                                BindingResult result, Model model, RedirectAttributes attributes, HttpServletResponse response) throws PessoaInexistenteOuInativaException {
        receitaBO.atualizaReceita(codigo, receita);
        if (result.hasErrors()) {
            receita.setCodigo(codigo);
            model.addAttribute("receita", receita);
            attributes.addFlashAttribute("mensagem", "Verifique se todos os campos foram preechidos!");
            return "updatereceita";
        }
        ReceitaDTO receitaDto = receitaBO.atualizaReceita(codigo, receita);
        model.addAttribute("receita", receitaDto);
        return "updatereceita";
    }

}
