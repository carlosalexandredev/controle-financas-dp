package com.example.demo.model.util;

import lombok.Data;

import java.util.List;

@Data
public class TotalizadorMoedaDTO<T> {

    List<T> listaFinancas;
    private T totalReal;
    private T totalDolar;
    private T totalEuro;

}
