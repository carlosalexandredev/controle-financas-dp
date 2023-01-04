package com.example.demo.token.entity;

import com.example.demo.usuario.entity.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "FT_CONFIRMA_TOKEN")
public class ConfirmaToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    @Column(nullable = false)
    private LocalDateTime expiraEm;

    private LocalDateTime confirmadoEm;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_usuario_id"
    )
    private Usuario usuario;

    public ConfirmaToken(String token,
                         LocalDateTime criadoEm,
                         LocalDateTime expireAt,
                         Usuario usuario) {
        this.token = token;
        this.criadoEm = criadoEm;
        this.expiraEm = expireAt;
        this.usuario = usuario;
    }
}
