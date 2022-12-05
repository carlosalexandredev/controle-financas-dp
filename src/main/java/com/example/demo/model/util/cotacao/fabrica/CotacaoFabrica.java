package com.example.demo.model.util.cotacao.fabrica;


import com.example.demo.model.util.cotacao.adpter.Cotacao;
import com.example.demo.model.util.cotacao.adpter.CotacaoDolar;
import com.example.demo.model.util.cotacao.adpter.CotacaoEuro;
import com.example.demo.model.util.enuns.TipoMoeda;

public class CotacaoFabrica {
    /** Factory Method */
    public static Cotacao getCotacao(TipoMoeda tipoMoeda) {
        switch (tipoMoeda){
            case DOLAR:  {return new CotacaoDolar();}
            case EURO:  {return new CotacaoEuro();}
            default: { throw new IllegalArgumentException("Opção de tipo de mensagem inválida!");}
        }
    }
}
