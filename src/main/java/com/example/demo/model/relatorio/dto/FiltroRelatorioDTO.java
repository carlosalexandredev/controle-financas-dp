package com.example.demo.model.relatorio.dto;

import com.example.demo.model.fortune.util.enuns.TipoMoeda;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class FiltroRelatorioDTO {
    private TipoMoeda moeda;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
}
