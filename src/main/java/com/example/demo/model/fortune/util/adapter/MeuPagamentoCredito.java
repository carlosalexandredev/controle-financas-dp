package com.example.demo.model.fortune.util.adapter;

import java.math.BigDecimal;

class MeuPagamentoCredito extends SdkPagamentoCredito
        implements ProcessorPagamento {

    // ...

    public void debitar(BigDecimal valor) {
        super.autorizar(valor);
        super.capturar(valor);
    }

    public void creditar(BigDecimal valor) {
        super.creditar(valor);
    }
}
