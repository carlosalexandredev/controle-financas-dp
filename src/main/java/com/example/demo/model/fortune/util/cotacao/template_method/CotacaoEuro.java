package com.example.demo.model.fortune.util.cotacao.template_method;

import com.example.demo.model.fortune.util.cotacao.dto.CotacaoDTO;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigDecimal;

public class CotacaoEuro extends CotacaoAPI{

    @Override
    protected String getRequestKey() {
        return "EUR-BRL";
    }

    @Override
    protected String getResponseKey() {
        return "EURBRL";
    }

    @Test
    @DisplayName("Teste de classe e comunicação com API de Cotação")
    public static void testeRetorno() throws IOException {
        CotacaoEuro ct = new CotacaoEuro();
        CotacaoDTO euroDto = ct.consultaCotacao(new BigDecimal(500));
        System.out.println(euroDto.toString());
    }
}
