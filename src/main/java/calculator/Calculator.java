package calculator;

import java.math.BigDecimal;
import java.util.List;

public class Calculator {

    public static BigDecimal add(List<BigDecimal> decimalList) {
        BigDecimal result = BigDecimal.ZERO;
        for (BigDecimal decimal : decimalList) {
            result = result.add(decimal);
        }
        return result;
    }
}
