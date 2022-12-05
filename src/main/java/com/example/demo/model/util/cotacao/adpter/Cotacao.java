package com.example.demo.model.util.cotacao.adpter;

import java.io.IOException;
import java.math.BigDecimal;

public interface Cotacao<DTO> {
    DTO consultaCotacao(BigDecimal valor) throws IOException;
}
