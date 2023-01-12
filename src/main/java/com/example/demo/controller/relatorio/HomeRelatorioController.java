package com.example.demo.controller.relatorio;

import com.example.demo.model.relatorio.dto.FiltroRelatorioDTO;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/home-relatorio")
public interface HomeRelatorioController {
    @RequestMapping(value = {"/relatorio"}, method = RequestMethod.GET)
    String relatorio(FiltroRelatorioDTO filtroReq, ModelMap model);

}