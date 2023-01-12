package com.example.demo.model.relatorio.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RelatorioDTO {
    private FiltroRelatorioDTO filtro;
    private String receitaReal;
    private String receitaEuro;
    private String receitaDolar;

    private String investimentoReal;
    private String investimentoEuro;
    private String investimentoDolar;

    private String despesaReal;
    private String despesaEuro;
    private String despesaDolar;

    private String totalReal;
    private String totalEuro;
    private String totalDolar;

}
