package com.example.demo.relatorio.controller;

import com.example.demo.relatorio.dto.FiltroRelatorioDTO;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/home-relatorio")
public interface HomeRelatorioController {
    @RequestMapping(value = {"/relatorio"}, method = RequestMethod.GET)
    String relatorio(FiltroRelatorioDTO filtroReq, ModelMap model);

}