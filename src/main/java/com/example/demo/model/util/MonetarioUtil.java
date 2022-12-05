package com.example.demo.model.util;

/**
 * @TAG SG02
 * Singleton - Design Pattern Criacional
 * @Author ->>Seu nome<<--
 * */

public class MonetarioUtil {



//    private final static NumberFormat REAL_FORMATO = getNumberInstance(new Locale("pt", "BR"));
//    private final NumberFormat euFormat = getNumberInstance(new Locale("en", "US"));
//    private final NumberFormat esFormat = getNumberInstance(new Locale("es", "ES"));
//

//    public static final int DIFF_TAM_MONETARIO = 2;


//    static
//    {
//        DECIMAL_FORMAT.setMinimumFractionDigits(FRACTION_DIGITS);
//    }

    /**
     * Método construtor privado
     * Criar metodo getInstance
     *
     * Ex.:
     * public static ModelMapper getInstance() {
     *         if(Objects.isNull(modelMapper)){ <-- Verificar se já existe instancio do objeto
     *             modelMapper = new ModelMapper(); <-- Se não existir crie um objeto
     *         }
     *         return modelMapper; <-- caso o objeto já existe retorne-o.
     *     }
     *
     * */



//    public String monetarios(Number valor, Integer tam, TipoMoeda tipo)
//    {
//        if (valor instanceof Integer || valor instanceof BigDecimal)
//        {
//            switch (tipo) {
//                case REAL:
//                    return "R$" + right(DECIMAL_FORMAT.format(valor), tam - DIFF_TAM_MONETARIO);
//                case DOLAR:
//                    return "US$" + right(euFormat.format(valor), tam - DIFF_TAM_MONETARIO);
//                case EURO:
//                    return "€" + right(esFormat.format(valor), tam - DIFF_TAM_MONETARIO);
//                default:
//                    return String.valueOf(valor);
//            }
//        }
//        return "";
//    }
}
