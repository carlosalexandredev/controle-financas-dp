package com.example.demo.usuario.service;

import com.example.demo.email.entity.Email;
import com.example.demo.token.entity.ConfirmaToken;
import com.example.demo.usuario.entity.Usuario;
import com.example.demo.usuario.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.token.service.ConfirmaTokenService;
import com.example.demo.email.service.EmailAComfimacaoService;


import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioService implements UserDetailsService {
    @Autowired
    private ConfirmaTokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
//    @Autowired
//    private EmailAComfimacaoService emailConfimacaoService;
    private final static String USER_NOT_FOUND_MSG = "Usuario não encontrado email";

    /** Verifica o se usuario informado no login existe na base de dados */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(Usuario usuario){
        verficaUsuario(usuario);
        salvaUsuario(usuario);
        enviaEmailConfimacao(usuario, salvaToken(usuario));
        return "Usuário Cadastrado Confirme seu E-mail";
    }

    public int enableAppUser(String email) {
        return usuarioRepository.enableAppUser(email);
    }

    private void enviaEmailConfimacao(Usuario usuario, String token) {
//        String link =  System.getenv().getOrDefault("HOST", "http://localhost:8080") + "/api/v1/registration/confirm?token=" + token;
//
//        Email email = Email.builder()
//                .subject("Confirmação de Cadastro")
//                .urlConfirme(link)
//                .usuario(usuario)
//                .build();
//        emailConfimacaoService.enviaEmail(email);
    }
    private String salvaToken(Usuario usuario) {
        String token = UUID.randomUUID().toString();
        ConfirmaToken confirmaToken = new ConfirmaToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                usuario
        );
        tokenService.saveConfirmationToken(confirmaToken);
        return token;
    }
    private void salvaUsuario(Usuario usuario) {
        String encodedPassword = bCryptPasswordEncoder
                .encode(usuario.getPassword());

        usuario.setSenha(encodedPassword);

        usuarioRepository.save(usuario);
    }
    private void verficaUsuario(Usuario usuario) {
        Boolean UsuarioExists = usuarioRepository
                .findByEmail(usuario.getEmail())
                .isPresent();
        if(UsuarioExists){
            throw new IllegalStateException("E-mail já utilizado");
        }
    }


}

