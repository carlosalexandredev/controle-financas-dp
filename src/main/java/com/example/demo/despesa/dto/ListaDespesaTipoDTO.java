package com.example.demo.despesa.dto;

import com.example.demo.despesa.dto.DespesaDTO;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
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
