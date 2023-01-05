package com.example.demo.receita.controller;

import com.example.demo.fortune.exceptions.PessoaInexistenteOuInativaException;
import com.example.demo.receita.dto.ReceitaDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequestMapping("/home-receita")
public interface HomeReceitaController {

    @RequestMapping(value = "/salva-receita", method = RequestMethod.POST)
    String salvarReceita(@Valid @ModelAttribute ReceitaDTO receita, BindingResult result, RedirectAttributes attributes, HttpServletResponse response) throws PessoaInexistenteOuInativaException;

    @RequestMapping(value = "/delete_receita", method = RequestMethod.GET)
    String handleDeleteReceita(@RequestParam(name = "codigo") Long codigo);

    @GetMapping("/editar-receita/{codigo}")
    String editarReceita(@PathVariable long codigo, Model model) throws
            PessoaInexistenteOuInativaException;

    @PostMapping("/update_receita/{codigo}")
    String updateReceita(@PathVariable("codigo") long codigo, @Valid ReceitaDTO despesa,
                         BindingResult result, Model model, RedirectAttributes attributes, HttpServletResponse response) throws
            PessoaInexistenteOuInativaException;

}