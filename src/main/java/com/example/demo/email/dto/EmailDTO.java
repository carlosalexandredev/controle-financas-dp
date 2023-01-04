package com.example.demo.email.dto;

import com.example.demo.usuario.entity.Usuario;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class EmailDTO {
    @NotBlank
    private String ownerRef;
    @NotBlank
    @Email
    private String emailTo;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;
    @NotBlank
    private String urlConfirme;
    @NotBlank
    private Usuario usuario;

    public String getEmailFrom(){
        return System.getenv("EMAIL_BOOT");
    }
}
