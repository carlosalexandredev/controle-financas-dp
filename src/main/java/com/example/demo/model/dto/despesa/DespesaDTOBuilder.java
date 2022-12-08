package com.example.demo.model.dto.despesa;

import com.example.demo.model.dto.despesa.enuns.TipoDespesa;
import com.example.demo.model.entity.Pessoa;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public final class DespesaDTOBuilder {
    private Long codigo;
    private @NotNull @Size(max = 50) String nome;
    private @Size(max = 50) String setor;
    private @Size(max = 80) String descricao;
    private @NotNull LocalDate dataVencimento;
    private @NotNull BigDecimal valor;
    private @NotNull TipoDespesa tipodespesa;
    private @NotNull Pessoa pessoa;

    private DespesaDTOBuilder() {
    }

    public static DespesaDTOBuilder aDespesaDTO() {
        return new DespesaDTOBuilder();
    }

    public DespesaDTOBuilder codigo(Long codigo) {
        this.codigo = codigo;
        return this;
    }

    public DespesaDTOBuilder nome(String nome) {
        this.nome = nome;
        return this;
    }

    public DespesaDTOBuilder setor(String setor) {
        this.setor = setor;
        return this;
    }

    public DespesaDTOBuilder descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public DespesaDTOBuilder dataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
        return this;
    }

    public DespesaDTOBuilder valor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public DespesaDTOBuilder tipodespesa(TipoDespesa tipodespesa) {
        this.tipodespesa = tipodespesa;
        return this;
    }

    public DespesaDTOBuilder pessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
        return this;
    }

    public DespesaDTO build() {
        DespesaDTO despesaDTO = new DespesaDTO();
        despesaDTO.setCodigo(codigo);
        despesaDTO.setNome(nome);
        despesaDTO.setSetor(setor);
        despesaDTO.setDescricao(descricao);
        despesaDTO.setDataVencimento(dataVencimento);
        despesaDTO.setValor(valor);
        despesaDTO.setTipodespesa(tipodespesa);
        return despesaDTO;
    }
}
