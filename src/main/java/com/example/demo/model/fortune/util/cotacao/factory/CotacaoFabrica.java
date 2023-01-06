package com.example.demo.model.fortune.util.cotacao.factory;


import com.example.demo.model.fortune.util.cotacao.template_method.CotacaoAPI;
import com.example.demo.model.fortune.util.cotacao.template_method.CotacaoDolar;
import com.example.demo.model.fortune.util.cotacao.template_method.CotacaoEuro;
import com.example.demo.model.fortune.util.enuns.TipoMoeda;

public class CotacaoFabrica {
    /** Factory Method */
    public static CotacaoAPI getCotacao(TipoMoeda tipoMoeda) {
        switch (tipoMoeda){
            case DOLAR:  {return new CotacaoDolar();}
            case EURO:  {return new CotacaoEuro();}
            default: { throw new IllegalArgumentException("Opção de tipo de mensagem inválida!");}
        }
    }
}
