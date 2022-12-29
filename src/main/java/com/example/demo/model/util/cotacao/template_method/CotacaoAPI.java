package com.example.demo.model.util.cotacao.template_method;

import com.example.demo.model.util.cotacao.dto.CotacaoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
public abstract class CotacaoAPI<DTO> {

    private static final String BASE_URL = "https://economia.awesomeapi.com.br/last/";

    /** Template Method */
    protected abstract String getRequestKey();
    /** Template Method */
    protected abstract String getResponseKey();

    protected String cotacaoMoeda(String moeda) throws IOException {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(BASE_URL + moeda).newBuilder();
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .build();
        Call call = client.newCall(request);

        Response response = call.execute();
        return response.body().string();
    }

    /** Template Method */
    public CotacaoDTO consultaCotacao(BigDecimal valor) throws IOException {
        String response = cotacaoMoeda(getRequestKey());
        CotacaoDTO dolarJson = converteJsonParaDTO(valor, response);
        return dolarJson;
    }

    private CotacaoDTO converteJsonParaDTO(BigDecimal valor, String response) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(response);
        String baseKey = getResponseKey();

        CotacaoDTO dolarDto = CotacaoDTO.builder()
                .nome(rootNode.path(baseKey).path("name").asText())
                .sigla(rootNode.path(baseKey).path("code").asText())
                .valorPrimario(valor)
                .valorConvertido(converteMoeda(valor, rootNode.path(baseKey).path("bid").asText()))
                .build();
        return dolarDto;
    }

    private BigDecimal converteMoeda(BigDecimal valor, String bid) {
        BigDecimal valoFinal = valor.divide(new BigDecimal(bid), new MathContext(4));
        return valoFinal; //TODO: Corrigir valorFinal, fraccionar número.
    }

}

