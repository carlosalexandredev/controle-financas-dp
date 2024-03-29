package com.example.demo.model.conversor.cotacao.template_method;

import com.example.demo.model.fortune.util.MonetarioUtil;
import com.example.demo.model.conversor.cotacao.dto.CotacaoDTO;
import com.example.demo.model.fortune.util.enuns.TipoMoeda;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigDecimal;

public class CotacaoDolar extends CotacaoAPI<CotacaoDTO> {

    @Override
    protected String getRequestKey() {
        return "USD-BRL";
    }

    @Override
    protected String getResponseKey() {
        return "USDBRL";
    }

    @Test
    @DisplayName("Teste de classe e comunicação com API de Cotação")
    public static void testeRetorno() throws IOException {
        CotacaoDolar ct = new CotacaoDolar();
        CotacaoDTO dolarDto = ct.consultaCotacao(new BigDecimal(1));
        MonetarioUtil mon = MonetarioUtil.getInstance();
        System.out.println(mon.monetarios(dolarDto.getValorConvertido(), 17, TipoMoeda.DOLAR));
    }
}
