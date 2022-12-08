package com.example.demo.controller.receita;

import com.example.demo.model.bo.exceptionhandler.PessoaInexistenteOuInativaException;
import com.example.demo.model.dto.despesa.DespesaDTO;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequestMapping("/default")
public interface HomeReceitaController {

    @RequestMapping(value="/receita", method=RequestMethod.GET)
    public String despesas(ModelMap model);

    @RequestMapping(value="/salva-receita", method=RequestMethod.POST)
    public String salvarDespesa(@Valid @ModelAttribute DespesaDTO despesa, BindingResult result, RedirectAttributes attributes, HttpServletResponse response) throws PessoaInexistenteOuInativaException;

    @RequestMapping(value = "/delete-receita", method = RequestMethod.GET)
    public String handleDeleteDespesa(@RequestParam(name = "codigo") Long codigo);

    @GetMapping("/editar-receita/{codigo}")
    public String editarDespesa ( @PathVariable long codigo, Model model) throws
    PessoaInexistenteOuInativaException;

    @PostMapping("/update_receita/{codigo}")
    public String updateDespesa(@PathVariable("codigo") long codigo, @Valid DespesaDTO despesa,
                                BindingResult result, Model model, RedirectAttributes attributes, HttpServletResponse response) throws
    PessoaInexistenteOuInativaException;

    }