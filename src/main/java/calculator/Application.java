package calculator;

import calculator.parser.ParserDispatcher;
import calculator.util.Calculator;
import camp.nextstep.edu.missionutils.Console;

import java.math.BigDecimal;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        String line = Console.readLine();

        List<BigDecimal> decimalList = ParserDispatcher.parse(line);
        BigDecimal result = Calculator.add(decimalList);
        System.out.println("결과 : " + result);

        Console.close();
    }
}
