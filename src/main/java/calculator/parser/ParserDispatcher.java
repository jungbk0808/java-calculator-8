package calculator.parser;

import java.math.BigDecimal;
import java.util.List;

public class ParserDispatcher {

    public static List<BigDecimal> parse(String input) {
        if (input.startsWith("//")) {
            return CustomDelimiterParser.parse(input);
        }
        return DefaultDelimiterParser.parse(input);
    }
}
