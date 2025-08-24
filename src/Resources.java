import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Resources {

    public static BigDecimal valorParcelas(BigDecimal valorFinanciado, double jurosMes, int parcelas){
        BigDecimal fator = BigDecimal.valueOf(1).add(BigDecimal.valueOf(jurosMes));

        BigDecimal dividend =   valorFinanciado.multiply(fator.pow(parcelas)).
                                    multiply(BigDecimal.valueOf(jurosMes)).setScale(3,RoundingMode.HALF_UP);

        BigDecimal divisor  =   fator.pow(parcelas).subtract(BigDecimal.valueOf(1));

        return (dividend.divide(divisor, 2, RoundingMode.HALF_UP)).setScale(2,RoundingMode.HALF_DOWN);
    }

    public static String formatMoeda(BigDecimal valor) {

        DecimalFormat df = new DecimalFormat("#,##0.00");

        return df.format(valor);


    }

}


