package org.scratch.game.service.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.ConfigTestUtils;

class ResultCalculatorImplTest {

    final ResultCalculator calculator;

    ResultCalculatorImplTest() throws Exception {
        var config = ConfigTestUtils.getConfig();
        calculator = new ResultCalculatorImpl(config);
    }

    @Test
    void calculateResultWinTest() {
        var matrix = new String[][]{{"E", "F", "+500"}, {"D", "E", "F"}, {"D", "E", "F"}};

        var result = calculator.calculateResult(matrix, 100);

        Assertions.assertEquals(matrix, result.matrix());
        Assertions.assertEquals(950, result.reward());
        Assertions.assertEquals("+500", result.bonusSymbol());
    }

    @Test
    void calculateResultLooseTest() {
        var matrix = new String[][]{{"E", "F", "D"}, {"B", "C", "D"}, {"E", "C", "MISS"}};

        var result = calculator.calculateResult(matrix, 100);

        Assertions.assertEquals(matrix, result.matrix());
        Assertions.assertEquals(0, result.reward());
        Assertions.assertNull(result.bonusSymbol());
    }
}
