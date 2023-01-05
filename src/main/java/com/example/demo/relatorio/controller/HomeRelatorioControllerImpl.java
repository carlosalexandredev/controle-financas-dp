package com.example.demo.relatorio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

@Controller
public class HomeRelatorioControllerImpl implements HomeRelatorioController {

    @Override
    public String relatorio(ModelMap model){
        return "relatorio";
    }

}
