package calculator;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecimalParser {

    private static final String DEFAULT_DELIMITER_REGEX = "[,;]";
    private static final Pattern DEFAULT_DELIMITED_NUMBERS_PATTERN =
            Pattern.compile(
                    "(\\d+(\\.\\d+)?" + DEFAULT_DELIMITER_REGEX + ")*" + // 소수점 포함 숫자 + 구분자 반복
                    "\\d+(\\.\\d+)?"                                     // 마지막 숫자
            );

    private static final int CUSTOM_DELIMITER_PREFIX_LENGTH = 4;
    private static final String CUSTOM_DELIMITER = "delimiter";
    private static final Pattern CUSTOM_DELIMITED_NUMBERS_PATTERN =
            Pattern.compile(
                    "//(?<" + CUSTOM_DELIMITER + ">[^.\\d])\n" +                   // '.'과 숫자를 제외한 custom delimiter
                    "(\\d+(\\.\\d+)?\\k<" + CUSTOM_DELIMITER + ">)*\\d+(\\.\\d+)?" // 숫자와 delimiter 반복
            );

    public static List<BigDecimal> parse(String input) {
        if (input.startsWith("//")) {
            return parseCustomDelimiter(input);
        }
        return parseDefaultDelimiter(input);
    }

    private static List<BigDecimal> parseDefaultDelimiter(String string) {
        if (!DEFAULT_DELIMITED_NUMBERS_PATTERN.matcher(string).matches()) {
            throw new IllegalArgumentException("기본 구분자는 ,과 ;만 허용");
        }

        return toBigDecimalList(string, DEFAULT_DELIMITER_REGEX);
    }

    private static List<BigDecimal> parseCustomDelimiter(String string) {
        Matcher customMatcher = CUSTOM_DELIMITED_NUMBERS_PATTERN.matcher(string);

        if (!customMatcher.matches()) {
            throw new IllegalArgumentException("커스텀 구분자 형식에 맞지 않음");
        }

        String input = string.substring(CUSTOM_DELIMITER_PREFIX_LENGTH);
        String delimiter = Pattern.quote(customMatcher.group(CUSTOM_DELIMITER));

        return toBigDecimalList(input, delimiter);
    }

    private static List<BigDecimal> toBigDecimalList(String input, String delimiter) {
        String[] decimals = input.split(delimiter);

        return Arrays.stream(decimals)
                .map(BigDecimal::new)
                .toList();
    }
}
