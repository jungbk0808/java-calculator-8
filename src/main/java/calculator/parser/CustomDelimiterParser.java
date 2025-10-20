package calculator.parser;

import calculator.util.DecimalParser;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomDelimiterParser {

    private static final int CUSTOM_DELIMITER_PREFIX_LENGTH = 5;
    private static final Pattern CUSTOM_PREFIX_PATTERN = Pattern.compile("//.\\\\n", Pattern.DOTALL);

    private static final String CUSTOM_DELIMITER = "delimiter";
    private static final Pattern CUSTOM_DELIMITED_NUMBERS_PATTERN =
            Pattern.compile(
                    "//(?<" + CUSTOM_DELIMITER + ">[^.\\d])\\\\n" +                   // '.'과 숫자를 제외한 custom delimiter
                            "(\\d+(\\.\\d+)?\\k<" + CUSTOM_DELIMITER + ">)*\\d+(\\.\\d+)?" // 숫자와 delimiter 반복
            );

    public static boolean isCustomDelimiterFormat(String string) {
        return CUSTOM_PREFIX_PATTERN.matcher(string).lookingAt();
    }

    public static List<BigDecimal> parse(String string) {
        Matcher customMatcher = CUSTOM_DELIMITED_NUMBERS_PATTERN.matcher(string);

        if (!customMatcher.matches()) {
            throw new IllegalArgumentException("커스텀 구분자 형식에 맞지 않음");
        }

        String input = string.substring(CUSTOM_DELIMITER_PREFIX_LENGTH);
        String delimiter = Pattern.quote(customMatcher.group(CUSTOM_DELIMITER));

        return DecimalParser.toBigDecimalList(input, delimiter);
    }
}
