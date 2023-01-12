package com.example.demo.model.conversor.dto;

import com.example.demo.model.fortune.util.enuns.TipoMoeda;
import lombok.Data;

import java.math.BigDecimal;
@Data
public class ConversorDTO {
    private TipoMoeda moedaPrimaria;
    private BigDecimal valor;
    private TipoMoeda moedaSecundaria;
}
