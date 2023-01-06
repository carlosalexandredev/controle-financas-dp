package com.example.demo.model.investimento.entity;

import com.example.demo.model.fortune.util.enuns.TipoInvestimentos;
import com.example.demo.model.fortune.util.enuns.TipoMoeda;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "INVESTIMENTO")
public class Investimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INV_CODIGO")
    private Long codigo;
    @Column(name = "INV_NOME")
    private String nome;
    @Column(name = "INV_DESCRICAO")
    private String descricao;
    @Column(name = "INV_DATA")
    private LocalDate data;
    @Column(name = "INV_VALOR")
    private BigDecimal valor;
    @Column(name = "INV_TIPO")
    private TipoInvestimentos investimento;
    @Column(name = "INV_MOEDA")
    private TipoMoeda tipomoeda;
}