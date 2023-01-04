package com.example.demo.receita.dto;

import com.example.demo.fortune.util.enuns.TipoMoeda;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ReceitaDTO {
	private Long codigo;
	private String nome;
	private String descricao;
	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate data;
	private String dataformatada;
	private BigDecimal valor;
	private String valorformatado;
	private TipoMoeda tipomoeda;
}
