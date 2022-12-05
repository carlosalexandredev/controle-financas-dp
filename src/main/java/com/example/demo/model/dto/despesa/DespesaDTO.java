package com.example.demo.model.dto.despesa;

import com.example.demo.model.dto.despesa.enuns.TipoDespesa;
import com.example.demo.model.entity.Pessoa;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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
	private Pessoa pessoa;
	
}
