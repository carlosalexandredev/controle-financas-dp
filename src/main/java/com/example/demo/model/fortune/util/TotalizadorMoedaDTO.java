package com.example.demo.model.fortune.util;

import lombok.Data;

import java.util.List;

@Data
public class TotalizadorMoedaDTO<T> {

    List<T> listaFinancas;
    private T totalReal;
    private T totalDolar;
    private T totalEuro;

}
