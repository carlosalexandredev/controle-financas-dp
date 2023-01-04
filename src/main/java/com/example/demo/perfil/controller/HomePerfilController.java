package com.example.demo.perfil.controller;

import com.example.demo.fortune.exceptions.PessoaInexistenteOuInativaException;
import com.example.demo.perfil.dto.PerfilDTO;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequestMapping("/default")
public interface HomePerfilController {
    @RequestMapping(value = "/perfis", method = RequestMethod.GET)
    public String funcionario(ModelMap model);

    @RequestMapping(value = "/salva-perfil", method = RequestMethod.POST)
    public String salvarPessoa(@Valid @ModelAttribute PerfilDTO funcionario, BindingResult result, RedirectAttributes attributes, HttpServletResponse response);

    @RequestMapping(value = "/delete_usuario", method = RequestMethod.GET)
    public String handleDeletePessoa(@RequestParam(name = "codigo") Long codigo);

    @GetMapping("/editar/{codigo}")
    public String editarPessoa(@PathVariable long codigo, Model model) throws PessoaInexistenteOuInativaException;

    @PostMapping("/update/{codigo}")
    public String atualizaPessoa(@PathVariable("codigo") long codigo, @Valid PerfilDTO pessoa,
                                 BindingResult result, Model model, RedirectAttributes attributes) throws PessoaInexistenteOuInativaException;
}