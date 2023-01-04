package com.example.demo.registro.controller;

import com.example.demo.registro.dto.RegistroRequestDTO;
import com.example.demo.registro.service.RegistroService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "api/v1/registration")
public class RegistroController {
    @Autowired
    private RegistroService registroService;

    @PostMapping
    public String register(@RequestBody RegistroRequestDTO request){
        return registroService.register(request);
    }
    @GetMapping("confirm")
    public String confirm(@RequestParam("token") String token) {
        return registroService.confirmToken(token);
    }
}
