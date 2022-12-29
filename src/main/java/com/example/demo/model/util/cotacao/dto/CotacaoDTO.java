package com.example.demo.model.util.cotacao.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CotacaoDTO {
    private String nome;
    private String sigla;
    private BigDecimal valorPrimario;
    private BigDecimal valorConvertido;
    private String valorConvertidoFormatado;
}
