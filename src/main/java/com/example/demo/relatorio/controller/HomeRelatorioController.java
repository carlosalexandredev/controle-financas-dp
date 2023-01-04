package com.example.demo.relatorio.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/default")
public interface HomeRelatorioController {
    @RequestMapping(value={"/relatorio"}, method=RequestMethod.GET)
    public String relatorio(ModelMap model);

    }