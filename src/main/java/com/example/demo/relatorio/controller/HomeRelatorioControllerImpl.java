package com.example.demo.relatorio.controller;

import com.example.demo.receita.service.ReceitaService;
import com.example.demo.relatorio.dto.FiltroRelatorioDTO;
import com.example.demo.relatorio.dto.RelatorioDTO;
import com.example.demo.relatorio.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeRelatorioControllerImpl implements HomeRelatorioController {

    @Autowired
    private RelatorioService srvReceita;


    @Override
    public String relatorio(FiltroRelatorioDTO filtroReq, ModelMap model){
        return "relatorio";
    }

}
