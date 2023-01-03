package com.example.demo.controller.relatorio;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeRelatorioControllerImpl implements HomeRelatorioController {

    @Override
    public String relatorio(ModelMap model){
        return "relatorio";
    }

}
