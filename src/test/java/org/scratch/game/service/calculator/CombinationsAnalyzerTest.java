package org.scratch.game.service.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ConfigTestUtils;

class CombinationsAnalyzerTest {
    CombinationsAnalyzer analyzer;

    @BeforeEach
    void setUp() throws Exception {
        var config = ConfigTestUtils.getConfig();
        analyzer = new CombinationsAnalyzer(config);
    }

    @Test
    void findWinCombinationsNoCombinationsTest() {
        var matrix = new String[][]{{"A", "B", "C"}, {"D", "E", "F"}, {"A", "B", "C"}};

        var combinations = analyzer.findWinCombinations(matrix);

        Assertions.assertTrue(combinations.isEmpty());
    }

    @Test
    void findWinCombinationsSameSymbolCombinationTest() {
        var matrix = new String[][]{{"A", "B", "C"}, {"D", "E", "A"}, {"A", "B", "C"}};

        var combinations = analyzer.findWinCombinations(matrix);

        Assertions.assertFalse(combinations.isEmpty());
        Assertions.assertEquals(1, combinations.size());
        Assertions.assertTrue(combinations.containsKey("A"));
        Assertions.assertEquals("same_symbol_3_times", combinations.get("A").get(0));
    }

    @Test
    void findWinCombinationsLinearSymbolCombinationTest() {
        var matrix = new String[][]{{"A", "B", "C"}, {"A", "E", "A"}, {"A", "B", "C"}};

        var combinations = analyzer.findWinCombinations(matrix);

        Assertions.assertFalse(combinations.isEmpty());
        Assertions.assertEquals(1, combinations.size());
        Assertions.assertTrue(combinations.containsKey("A"));
        Assertions.assertEquals(2, combinations.get("A").size());
        Assertions.assertTrue(combinations.get("A").contains("same_symbols_vertically"));
    }
}
