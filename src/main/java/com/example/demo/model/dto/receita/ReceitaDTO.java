package com.example.demo.model.dto.receita;

import com.example.demo.model.util.enuns.TipoMoeda;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReceitaDTO {
	private Long id;
	private String nome;
	private String descricao;
	private LocalDate data;
	private BigDecimal valor;
	private TipoMoeda moeda;
}
