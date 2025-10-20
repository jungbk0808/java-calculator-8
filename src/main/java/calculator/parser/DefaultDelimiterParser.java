package calculator.parser;

import calculator.util.DecimalParser;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

public class DefaultDelimiterParser {

    private static final String DEFAULT_DELIMITER_REGEX = "[,;]";
    private static final Pattern DEFAULT_DELIMITED_NUMBERS_PATTERN =
            Pattern.compile(
                    "(\\d+(\\.\\d+)?" + DEFAULT_DELIMITER_REGEX + ")*" + // 소수점 포함 숫자 + 구분자 반복
                            "\\d+(\\.\\d+)?"                                     // 마지막 숫자
            );

    public static List<BigDecimal> parse(String string) {
        if (!DEFAULT_DELIMITED_NUMBERS_PATTERN.matcher(string).matches()) {
            throw new IllegalArgumentException("기본 구분자는 ,과 ;만 허용");
        }

        return DecimalParser.toBigDecimalList(string, DEFAULT_DELIMITER_REGEX);
    }
}
