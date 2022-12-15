package com.example.demo.model.util.cotacao.template_method;

import okhttp3.*;

import java.io.IOException;
import java.math.BigDecimal;

/** Template Method */

public abstract class CotacaoAPI<DTO> {

    private static final String BASE_URL = "https://economia.awesomeapi.com.br/last/";
    protected String getCotacaoMoeda(String moeda) throws IOException {
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

    abstract DTO consultaCotacao(BigDecimal valor) throws IOException;
}

