package com.example.demo.model.dto.receita;

import com.example.demo.model.util.enuns.TipoMoeda;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public final class ReceitaDTOBuilder {
    private Long codigo;
    private String nome;
    private String descricao;
    private @NotNull LocalDate data;
    private String dataformatada;
    private BigDecimal valor;
    private String valorformatado;
    private TipoMoeda tipomoeda;

    private ReceitaDTOBuilder() {
    }

    public static ReceitaDTOBuilder builder() {
        return new ReceitaDTOBuilder();
    }

    public ReceitaDTOBuilder codigo(Long codigo) {
        this.codigo = codigo;
        return this;
    }

    public ReceitaDTOBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public ReceitaDTOBuilder descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public ReceitaDTOBuilder data(LocalDate data) {
        this.data = data;
        return this;
    }

    public ReceitaDTOBuilder dataformatada(String dataformatada) {
        this.dataformatada = dataformatada;
        return this;
    }

    public ReceitaDTOBuilder valor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public ReceitaDTOBuilder valorformatado(String valorformatado) {
        this.valorformatado = valorformatado;
        return this;
    }

    public ReceitaDTOBuilder tipomoeda(TipoMoeda tipomoeda) {
        this.tipomoeda = tipomoeda;
        return this;
    }

    public ReceitaDTO build() {
        ReceitaDTO receitaDTO = new ReceitaDTO();
        receitaDTO.setCodigo(codigo);
        receitaDTO.setNome(nome);
        receitaDTO.setDescricao(descricao);
        receitaDTO.setData(data);
        receitaDTO.setDataformatada(dataformatada);
        receitaDTO.setValor(valor);
        receitaDTO.setValorformatado(valorformatado);
        receitaDTO.setTipomoeda(tipomoeda);
        return receitaDTO;
    }
}
