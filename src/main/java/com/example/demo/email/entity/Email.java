package com.example.demo.email.entity;

import com.example.demo.email.enuns.StatusEmail;
import com.example.demo.usuario.entity.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString
@SuperBuilder
@NoArgsConstructor
@Table(name= "FT_EMAIL")
public class Email {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long emailId;
    private String emailTo;
    private String subject;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;
    private String urlConfirme;
    @ManyToOne
    private Usuario usuario;

    //remover
    public String getEmailFrom(){
        return System.getenv("EMAIL_BOOT");
    }

}
