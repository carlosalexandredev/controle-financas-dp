package com.example.demo.receita.entity;

import com.example.demo.fortune.util.enuns.TipoMoeda;
import com.example.demo.usuario.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "RECEITA")
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REC_CODIGO")
    private Long codigo;
    @Column(name = "REC_NOME")
    private String nome;
    @Column(name = "REC_DESCRICAO")
    private String descricao;
    @Column(name = "REC_DATA")
    private LocalDate data;
    @Column(name = "REC_VALOR")
    private BigDecimal valor;
    @Column(name = "REC_MOEDA")
    private TipoMoeda tipomoeda;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;
}
