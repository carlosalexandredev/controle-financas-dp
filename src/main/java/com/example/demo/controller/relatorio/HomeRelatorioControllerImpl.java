package com.example.demo.controller.relatorio;

import com.example.demo.model.relatorio.dto.FiltroRelatorioDTO;
import com.example.demo.model.relatorio.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

@Controller
public class HomeRelatorioControllerImpl implements HomeRelatorioController {

    @Autowired
    private RelatorioService srvReceita;


    @Override
    public String relatorio(FiltroRelatorioDTO filtroReq, ModelMap model){
        return "relatorio";
    }

}
