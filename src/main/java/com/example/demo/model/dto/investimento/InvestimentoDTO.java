package com.example.demo.model.dto.investimento;

import com.example.demo.model.util.enuns.TipoInvestimentos;
import com.example.demo.model.util.enuns.TipoMoeda;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
public class InvestimentoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty
    private String nome;
    @NotBlank
    @Size(max = 200)
    private String descricao;
    @NotNull
    private LocalDate data;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private TipoInvestimentos investimento;
    @NotNull
    private TipoMoeda moeda;

}
