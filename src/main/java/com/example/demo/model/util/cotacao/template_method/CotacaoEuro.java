package com.example.demo.model.util.cotacao.template_method;

import com.example.demo.model.util.cotacao.dto.EuroCotacaoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.testng.annotations.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;

public class CotacaoEuro extends CotacaoAPI<EuroCotacaoDTO>{

    @Override
    public EuroCotacaoDTO consultaCotacao(BigDecimal valor) throws IOException {
        String response = super.getCotacaoMoeda("EUR-BRL");
        EuroCotacaoDTO euro = converteJsonParaDTO(valor, response);
        return euro;
    }

    @NotNull
    private EuroCotacaoDTO converteJsonParaDTO(BigDecimal valor, String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response);
        String baseKey = "EURBRL";

        EuroCotacaoDTO euro = EuroCotacaoDTO.builder()
                .nome(rootNode.path(baseKey).path("name").asText())
                .sigla(rootNode.path(baseKey).path("code").asText())
                .valorReal(valor)
                .valorEuro(converteRealToEuro(valor, rootNode.path(baseKey).path("bid").asText()))
                .build();

        return euro;
    }
    private BigDecimal converteRealToEuro(BigDecimal valor, String bid) {
        BigDecimal valoFinal = valor.divide(new BigDecimal(bid), new MathContext(20));
        return valoFinal; //TODO: Corrigir valorFinal, fraccionar número.
    }

    @Test
    @DisplayName("Teste de classe e comunicação com API de Cotação")
    public static void testeRetorno() throws IOException {
        CotacaoEuro ct = new CotacaoEuro();
        var euroDto = ct.consultaCotacao(new BigDecimal(1));
        System.out.println(euroDto.toString());
    }

}
