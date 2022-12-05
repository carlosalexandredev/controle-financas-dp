package com.example.demo.model.util.cotacao.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class DolarCotacaoDTO {
    String nome;
    String sigla;
    BigDecimal valorReal;
    BigDecimal valorDolar;
}
