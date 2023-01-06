package com.example.demo.model.fortune.util.adapter;

import java.math.BigDecimal;

public class Adapter {
    
    public static void main(String[] args) {
        MeuPagamentoCredito credito = new MeuPagamentoCredito();
        credito.debitar(new BigDecimal("100"));
    }
    
}
