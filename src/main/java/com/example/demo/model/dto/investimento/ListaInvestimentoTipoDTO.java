package com.example.demo.model.dto.investimento;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
@Builder
public class ListaInvestimentoTipoDTO {
    private List<InvestimentoDTO> despesasFixas;
    private List<InvestimentoDTO> despesasVariaveis;
    private List<InvestimentoDTO> despesasExtraordinarias;
    private List<InvestimentoDTO> despesasRegulares;

    private BigDecimal totalFixas;
    private BigDecimal totalVariaveis;
    private BigDecimal totalExtraordinarias;
    private BigDecimal totalRegulares;
}
