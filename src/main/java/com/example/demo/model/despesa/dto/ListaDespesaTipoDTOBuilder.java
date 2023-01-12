package com.example.demo.model.despesa.dto;

import java.math.BigDecimal;
import java.util.List;

public final class ListaDespesaTipoDTOBuilder {
    private List<DespesaDTO> despesasFixas;
    private List<DespesaDTO> despesasVariaveis;
    private List<DespesaDTO> despesasExtraordinarias;
    private List<DespesaDTO> despesasRegulares;
    private BigDecimal totalFixas;
    private BigDecimal totalVariaveis;
    private BigDecimal totalExtraordinarias;
    private BigDecimal totalRegulares;

    private ListaDespesaTipoDTOBuilder() {
    }

    public static ListaDespesaTipoDTOBuilder builder() {
        return new ListaDespesaTipoDTOBuilder();
    }

    public ListaDespesaTipoDTOBuilder despesasFixas(List<DespesaDTO> despesasFixas) {
        this.despesasFixas = despesasFixas;
        return this;
    }

    public ListaDespesaTipoDTOBuilder despesasVariaveis(List<DespesaDTO> despesasVariaveis) {
        this.despesasVariaveis = despesasVariaveis;
        return this;
    }

    public ListaDespesaTipoDTOBuilder despesasExtraordinarias(List<DespesaDTO> despesasExtraordinarias) {
        this.despesasExtraordinarias = despesasExtraordinarias;
        return this;
    }

    public ListaDespesaTipoDTOBuilder despesasRegulares(List<DespesaDTO> despesasRegulares) {
        this.despesasRegulares = despesasRegulares;
        return this;
    }

    public ListaDespesaTipoDTOBuilder totalFixas(BigDecimal totalFixas) {
        this.totalFixas = totalFixas;
        return this;
    }

    public ListaDespesaTipoDTOBuilder totalVariaveis(BigDecimal totalVariaveis) {
        this.totalVariaveis = totalVariaveis;
        return this;
    }

    public ListaDespesaTipoDTOBuilder totalExtraordinarias(BigDecimal totalExtraordinarias) {
        this.totalExtraordinarias = totalExtraordinarias;
        return this;
    }

    public ListaDespesaTipoDTOBuilder totalRegulares(BigDecimal totalRegulares) {
        this.totalRegulares = totalRegulares;
        return this;
    }

    public ListaDespesaTipoDTO build() {
        ListaDespesaTipoDTO listaDespesaTipoDTO = new ListaDespesaTipoDTO();
        listaDespesaTipoDTO.setDespesasFixas(despesasFixas);
        listaDespesaTipoDTO.setDespesasVariaveis(despesasVariaveis);
        listaDespesaTipoDTO.setDespesasExtraordinarias(despesasExtraordinarias);
        listaDespesaTipoDTO.setDespesasRegulares(despesasRegulares);
        listaDespesaTipoDTO.setTotalFixas(totalFixas);
        listaDespesaTipoDTO.setTotalVariaveis(totalVariaveis);
        listaDespesaTipoDTO.setTotalExtraordinarias(totalExtraordinarias);
        listaDespesaTipoDTO.setTotalRegulares(totalRegulares);
        return listaDespesaTipoDTO;
    }
}