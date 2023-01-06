package com.example.demo.model.investimento.dto;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Data
@Builder
public class ListaInvestimentoTipoDTO {
    private List<InvestimentoDTO> tesouroDireto;
    private List<InvestimentoDTO> cdb;
    private List<InvestimentoDTO> acoes;
    private List<InvestimentoDTO> fundosImobiliarios;

    private BigDecimal totalFixas;
    private BigDecimal totalVariaveis;
    private BigDecimal totalExtraordinarias;
    private BigDecimal totalRegulares;
}
