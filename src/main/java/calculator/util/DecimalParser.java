package calculator.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class DecimalParser {

    private DecimalParser() {}

    public static List<BigDecimal> toBigDecimalList(String input, String delimiter) {
        String[] decimals = input.split(delimiter);

        return Arrays.stream(decimals)
                .map(BigDecimal::new)
                .toList();
    }
}
