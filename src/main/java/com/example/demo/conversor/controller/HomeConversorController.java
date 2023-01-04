package com.example.demo.conversor.controller;

import com.example.demo.conversor.dto.ConversorDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("/default")
public interface HomeConversorController {

    @RequestMapping(value="/conversor", method=RequestMethod.GET)
    public String conversorDados(@ModelAttribute ConversorDTO conversor, Model model) throws IOException;
    }