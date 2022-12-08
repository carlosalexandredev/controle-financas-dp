package com.example.demo.controller.conversor;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/default")
public interface HomeConversorController {

    @RequestMapping(value="/conversor", method=RequestMethod.GET)
    public String conversor(ModelMap model);

    }