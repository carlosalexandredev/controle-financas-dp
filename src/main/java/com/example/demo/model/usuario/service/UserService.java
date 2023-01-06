package com.example.demo.model.usuario.service;

import com.example.demo.model.usuario.dto.UserRegistrationDTO;
import com.example.demo.model.usuario.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDTO registrationDto);
}
