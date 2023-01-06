package com.example.demo.controller.perfil;

import com.example.demo.model.fortune.exceptions.PessoaInexistenteOuInativaException;
import com.example.demo.model.perfil.dto.PerfilDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequestMapping("/home-perfil")
public interface HomePerfilController {

    @RequestMapping(value = "/salva-perfil", method = RequestMethod.POST)
    String salvarPessoa(@Valid @ModelAttribute PerfilDTO perfilDTO, BindingResult result, RedirectAttributes attributes, HttpServletResponse response);

    @RequestMapping(value = "/delete_usuario", method = RequestMethod.GET)
    String handleDeletePessoa(@RequestParam(name = "codigo") Long codigo);

    @GetMapping("/editar/{codigo}")
    String editarPessoa(@PathVariable long codigo, Model model) throws PessoaInexistenteOuInativaException;

    @PostMapping("/update/{codigo}")
    String atualizaPessoa(@PathVariable("codigo") long codigo, @Valid PerfilDTO pessoa,
                          BindingResult result, Model model, RedirectAttributes attributes) throws PessoaInexistenteOuInativaException;
}