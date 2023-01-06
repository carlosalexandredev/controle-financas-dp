package com.example.demo.model.investimento.dto;

import com.example.demo.model.fortune.util.enuns.TipoInvestimentos;
import com.example.demo.model.fortune.util.enuns.TipoMoeda;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InvestimentoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long codigo;
    @NotEmpty
    private String nome;
    @NotBlank
    @Size(max = 200)
    private String descricao;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataInvestimento;
    @NotNull
    private BigDecimal valor;
    @NotNull
    private TipoInvestimentos investimento;
    @NotNull
    private TipoMoeda tipomoeda;

    private String valorFormatado;

    private String dataFormatada;

}
