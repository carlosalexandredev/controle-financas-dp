package com.example.demo.usuario.controller;

import com.example.demo.usuario.dto.UsuarioDTO;
import com.example.demo.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<String> findAll(){
        return ResponseEntity.ok().body("OK");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> findById(@NotBlank @PathVariable("id") Long id){
        return ResponseEntity.ok().body("OK");
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody UsuarioDTO usuario){
        return ResponseEntity.ok().body("OK");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuario){
        return ResponseEntity.ok().body("OK");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@NotBlank @PathVariable("id") Long id){
        return ResponseEntity.ok().body("OK");
    }
}