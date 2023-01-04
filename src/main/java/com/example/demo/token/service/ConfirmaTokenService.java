package com.example.demo.token.service;

import com.example.demo.token.entity.ConfirmaToken;
import com.example.demo.token.repository.ConfirmaTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmaTokenService {
    private final ConfirmaTokenRepository confirmaTokenRepository;
    public void saveConfirmationToken(ConfirmaToken token){
        confirmaTokenRepository.save(token);
    }

    public Optional<ConfirmaToken> getToken(String token) {
        return confirmaTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmaTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

}
