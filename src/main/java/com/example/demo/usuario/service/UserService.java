package com.example.demo.usuario.service;

import com.example.demo.usuario.dto.UserRegistrationDTO;
import com.example.demo.usuario.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDTO registrationDto);

    User findUserByEmail(String email);

    User getUser();
}
