package com.example.demo.model.receita.entity;
import com.example.demo.model.fortune.util.enuns.TipoMoeda;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
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
}
