package com.example.demo.relatorio.dto;

import com.example.demo.fortune.util.enuns.TipoMoeda;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class FiltroRelatorioDTO {
    private TipoMoeda moeda;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;
}
