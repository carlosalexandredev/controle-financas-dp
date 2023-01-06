package com.example.demo.model.fortune.util.adapter;

import java.math.BigDecimal;

interface ProcessorPagamento {

    void debitar(BigDecimal valor);

    void creditar(BigDecimal valor);

}
