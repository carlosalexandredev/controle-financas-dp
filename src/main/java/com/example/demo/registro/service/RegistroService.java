package com.example.demo.registro.service;

import com.example.demo.registro.dto.RegistroRequestDTO;
import com.example.demo.usuario.entity.Usuario;
import com.example.demo.usuario.enuns.UsuarioRole;
import com.example.demo.token.entity.ConfirmaToken;
import com.example.demo.token.service.ConfirmaTokenService;
import com.example.demo.usuario.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class RegistroService {
    private UsuarioService usuarioService;
    private final ConfirmaTokenService confirmaTokenService;


    public String register(RegistroRequestDTO request) {
        return usuarioService.signUpUser(
                new Usuario(
                        request.getNome(),
                        request.getEmail(),
                        request.getSenha(),
                        request.getNascimento(),
                        request.getTelefone(),
                        UsuarioRole.USER)
        );
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmaToken confirmaToken = confirmaTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("MoblixClient não encontrado"));

        if (Objects.nonNull(confirmaToken.getConfirmadoEm())) {
            throw new IllegalStateException("O E-mail já foi confimado");
        }

        LocalDateTime expiredAt = confirmaToken.getExpiraEm();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("MoblixClient expirado");
        }

        confirmaTokenService.setConfirmedAt(token);
        usuarioService.enableAppUser(
                confirmaToken.getUsuario().getEmail());
        return "E-mail Confirmado";
    }
}
