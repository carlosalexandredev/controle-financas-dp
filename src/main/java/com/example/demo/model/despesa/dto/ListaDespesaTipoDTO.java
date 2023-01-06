package com.example.demo.model.despesa.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ListaDespesaTipoDTO {
	private List<DespesaDTO> despesasFixas;
	private List<DespesaDTO> despesasVariaveis;
	private List<DespesaDTO> despesasExtraordinarias;
	private List<DespesaDTO> despesasRegulares;

	private BigDecimal totalFixas;
	private BigDecimal totalVariaveis;
	private BigDecimal totalExtraordinarias;
	private BigDecimal totalRegulares;
}
