package com.example.demo.controller.investimento;

import com.example.demo.model.bo.exceptionhandler.PessoaInexistenteOuInativaException;
import com.example.demo.model.dto.despesa.DespesaDTO;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequestMapping("/investimento")
public interface HomeInvestimentoController {

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String investimento(ModelMap model);

    @RequestMapping(value="/salva-despesa", method=RequestMethod.POST)
    public String salvarInvestimento(@Valid @ModelAttribute DespesaDTO despesa, BindingResult result, RedirectAttributes attributes, HttpServletResponse response) throws PessoaInexistenteOuInativaException;

    @RequestMapping(value = "/delete_despesa", method = RequestMethod.GET)
    public String handleDeleteInvestimento(@RequestParam(name = "codigo") Long codigo);

    @GetMapping("/editar-despesa/{codigo}")
    public String editarInvestimento ( @PathVariable long codigo, Model model) throws
    PessoaInexistenteOuInativaException;

    @PostMapping("/update_despesa/{codigo}")
    public String updateInvestimento(@PathVariable("codigo") long codigo, @Valid DespesaDTO despesa,
                                BindingResult result, Model model, RedirectAttributes attributes, HttpServletResponse response) throws
    PessoaInexistenteOuInativaException;

    }