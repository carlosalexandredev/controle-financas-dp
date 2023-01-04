package com.example.demo.usuario.entity;

import com.example.demo.usuario.enuns.UsuarioRole;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@SuperBuilder
@NoArgsConstructor
@Table(name = "FT_ROLE")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UsuarioRole role;
}
