package com.example.demo.investimento.controller;

import com.example.demo.fortune.exceptions.PessoaInexistenteOuInativaException;
import com.example.demo.investimento.dto.InvestimentoDTO;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequestMapping("/home-investimento")
public interface HomeInvestimentoController {

    @RequestMapping(value = "/salva-investimento", method = RequestMethod.POST)
    String salvarInvestimento(@Valid @ModelAttribute InvestimentoDTO investimento, BindingResult result, RedirectAttributes attributes, HttpServletResponse response) throws PessoaInexistenteOuInativaException;

    @RequestMapping(value = "/delete-investimento", method = RequestMethod.GET)
    String handleDeleteInvestimento(@RequestParam(name = "codigo") Long codigo);

    @GetMapping("/editar-investimento/{codigo}")
    String editarInvestimento(@PathVariable long codigo, Model model);

    @PostMapping("/update_investimento/{codigo}")
    String updateInvestimento(@PathVariable("codigo") long codigo, @Valid InvestimentoDTO investimento,
                              BindingResult result, Model model, RedirectAttributes attributes, HttpServletResponse response) throws
            PessoaInexistenteOuInativaException;

}
