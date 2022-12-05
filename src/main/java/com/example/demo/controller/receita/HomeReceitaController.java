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

@RequestMapping("/receita")
public interface HomeReceitaController {

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String despesas(ModelMap model);

    @RequestMapping(value="/salva-despesa", method=RequestMethod.POST)
    public String salvarDespesa(@Valid @ModelAttribute DespesaDTO despesa, BindingResult result, RedirectAttributes attributes, HttpServletResponse response) throws PessoaInexistenteOuInativaException;

    @RequestMapping(value = "/delete_despesa", method = RequestMethod.GET)
    public String handleDeleteDespesa(@RequestParam(name = "codigo") Long codigo);

    @GetMapping("/editar-despesa/{codigo}")
    public String editarDespesa ( @PathVariable long codigo, Model model) throws
    PessoaInexistenteOuInativaException;

    @PostMapping("/update_despesa/{codigo}")
    public String updateDespesa(@PathVariable("codigo") long codigo, @Valid DespesaDTO despesa,
                                BindingResult result, Model model, RedirectAttributes attributes, HttpServletResponse response) throws
    PessoaInexistenteOuInativaException;

    }