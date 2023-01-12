package com.example.demo.model.fortune.util;

import com.example.demo.model.fortune.util.enuns.TipoMoeda;
import org.apache.commons.lang3.StringUtils;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

import static java.text.NumberFormat.getNumberInstance;
import static org.apache.commons.lang3.StringUtils.right;

public class MonetarioUtil {

    private static MonetarioUtil monetarioUtil;

    private MonetarioUtil() {
    }

    public static MonetarioUtil getInstance() {
        if(Objects.isNull(monetarioUtil)){
            monetarioUtil = new MonetarioUtil();
        }
        return monetarioUtil;
    }

    private final static NumberFormat DECIMAL_FORMAT =
            DecimalFormat.getNumberInstance(new Locale("pt", "BR"));
    private final NumberFormat euFormat = getNumberInstance(new Locale("en", "US"));
    private final NumberFormat esFormat = getNumberInstance(new Locale("es", "ES"));
    private static final int FRACTION_DIGITS = 2;
    public static final int DIFF_TAM_MONETARIO = 2;
    static {DECIMAL_FORMAT.setMinimumFractionDigits(FRACTION_DIGITS);}
    public String monetarios(Number valor, Integer tam, TipoMoeda tipo) {
        if (valor instanceof Integer || valor instanceof BigDecimal)
        {
            switch (tipo) {
                case REAL:
                    return "R$ " + right(DECIMAL_FORMAT.format(valor), tam - DIFF_TAM_MONETARIO);
                case DOLAR:
                    return "US$ " + right(euFormat.format(valor), tam - DIFF_TAM_MONETARIO);
                case EURO:
                    return "€ " + right(esFormat.format(valor), tam - DIFF_TAM_MONETARIO);
                default:
                    return String.valueOf(valor);
            }
        }
        return StringUtils.EMPTY;
    }
}
