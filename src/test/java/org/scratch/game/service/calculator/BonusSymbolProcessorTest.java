package org.scratch.game.service.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.scratch.game.model.config.Config;
import util.ConfigTestUtils;

class BonusSymbolProcessorTest {
    Config config = ConfigTestUtils.getConfig();
    BonusSymbolProcessor processor = new BonusSymbolProcessor(config);

    public BonusSymbolProcessorTest() throws Exception {
    }

    @Test
    void findBonusSymbolExistsTest() {
        var matrix = new String[][]{{"A", "10x", "C"}, {"D", "E", "F"}, {"A", "B", "C"}};

        var result = processor.findBonusSymbol(matrix);

        Assertions.assertEquals("10x", result);
    }

    @Test
    void findBonusSymbolPresentTest() {
        var matrix = new String[][]{{"A", "10x", "C"}, {"D", "E", "F"}, {"A", "B", "C"}};

        var result = processor.findBonusSymbol(matrix);

        Assertions.assertEquals("10x", result);
    }

    @Test
    void findBonusSymbolNotPresentTest() {
        var matrix = new String[][]{{"A", "A", "C"}, {"D", "E", "F"}, {"A", "B", "C"}};

        var result = processor.findBonusSymbol(matrix);

        Assertions.assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"+500", "+1000"})
    void applyBonusSymbolExtraTest(String bonus) {
        var reward = 1000;

        var result = processor.applyBonusSymbol(bonus, reward);

        Assertions.assertEquals(reward + Double.parseDouble(bonus), result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"5x", "10x"})
    void applyBonusSymbolMultiplyTest(String bonus) {
        var reward = 1000;

        var result = processor.applyBonusSymbol(bonus, reward);

        Assertions.assertEquals(reward * Double.parseDouble(bonus.substring(0, bonus.length() - 1)), result);
    }

    @Test
    void applyBonusSymbolMissTest() {
        var reward = 1000;

        var result = processor.applyBonusSymbol("MISS", reward);

        Assertions.assertEquals(reward, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"5x", "10x", "+500", "+1000"})
    void isBonusApplicableTest(String bonus) {
        Assertions.assertTrue(processor.isBonusApplicable(bonus));
    }

    @Test
    void isBonusApplicableMissTest() {
        Assertions.assertFalse(processor.isBonusApplicable("MISS"));
    }
}
