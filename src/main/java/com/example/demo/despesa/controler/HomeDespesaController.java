package com.example.demo.despesa.controler;

import com.example.demo.despesa.dto.DespesaDTO;
import com.example.demo.fortune.exceptions.PessoaInexistenteOuInativaException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequestMapping("/home-despesa")
public interface HomeDespesaController {

    @RequestMapping(value = "/salva-despesa", method = RequestMethod.POST)
    String salvarDespesa(@Valid @ModelAttribute DespesaDTO despesa, BindingResult result, RedirectAttributes attributes, HttpServletResponse response) throws PessoaInexistenteOuInativaException;

    @RequestMapping(value = "/delete_despesa", method = RequestMethod.GET)
    String handleDeleteDespesa(@RequestParam(name = "codigo") Long codigo);

    @GetMapping("/editar-despesa/{codigo}")
    String editarDespesa(@PathVariable long codigo, Model model) throws
            PessoaInexistenteOuInativaException;

    @PostMapping("/update_despesa/{codigo}")
    String updateDespesa(@PathVariable("codigo") long codigo, @Valid DespesaDTO despesa,
                         BindingResult result, Model model, RedirectAttributes attributes, HttpServletResponse response) throws
            PessoaInexistenteOuInativaException;

}