package com.example.demo.model.despesa.dto;

import com.example.demo.model.despesa.enuns.TipoDespesa;
import com.example.demo.model.fortune.util.enuns.TipoMoeda;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class DespesaDTO {
	private Long codigo;

	@NotNull
	@Size(max = 50)
	private String nome;

	@Size(max = 50)
	private String setor;

	@Size(max = 80)
	private String descricao;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataVencimento;

	@NotNull
	private BigDecimal valor;

	@NotNull
	private TipoDespesa tipodespesa;

	@NotNull
	private TipoMoeda tipomoeda;
	
}
