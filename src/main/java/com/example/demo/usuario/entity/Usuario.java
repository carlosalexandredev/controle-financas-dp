package com.example.demo.usuario.entity;

import com.example.demo.fortune.entity.PrimaryEntity;
import com.example.demo.usuario.enuns.UsuarioRole;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "FT_USUARIO")
public class Usuario extends PrimaryEntity implements UserDetails {
    private String nome;
    private String email;
    private String senha;
    private LocalDate nascimento;
    private String telefone;
    private LocalDate dataCriacao = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private UsuarioRole role;
    private Boolean locked = false;
    private Boolean enabled = false;

    public Usuario(String nome, String email, String senha, LocalDate nascimento, String telefone, UsuarioRole role) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authorities = new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authorities);
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
