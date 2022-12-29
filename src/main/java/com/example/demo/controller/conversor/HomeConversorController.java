package com.example.demo.controller.conversor;

import com.example.demo.model.dto.conversor.ConversorDTO;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/default")
public interface HomeConversorController {

    @RequestMapping(value="/conversor", method=RequestMethod.GET)
    public String conversorDados(@ModelAttribute ConversorDTO conversor, Model model) throws IOException;
    }