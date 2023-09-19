package org.scratch.game.service.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.scratch.game.model.config.Config;
import util.ConfigTestUtils;

import java.util.List;
import java.util.Map;

class CombinationsRewardCounterTest {
    CombinationsRewardCounter counter;

    Config config;

    @BeforeEach
    void setUp() throws Exception {
        config = ConfigTestUtils.getConfig();
        counter = new CombinationsRewardCounter(config);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void countResultEmptyTest(Map<String, List<String>> combinations) {
        Assertions.assertEquals(0, counter.countResult(combinations, 1000));
    }

    @Test
    void countResultOneCombinationTest() {
        var combinationName = "same_symbol_3_times";
        var bet = 100;
        var symbol = "A";
        var combinations = Map.of(symbol, List.of(combinationName));

        var result = counter.countResult(combinations, bet);

        Assertions.assertEquals(bet * config.symbols().get(symbol).rewardMultiplier() * config.winCombinations().get(combinationName).rewardMultiplier(), result);
    }

    @Test
    void countResultMultipleCombinationsTest() {
        var combinationName0 = "same_symbol_3_times";
        var combinationName1 = "same_symbols_horizontally";
        var bet = 100;
        var symbol = "A";
        var combinations = Map.of(symbol, List.of(combinationName0, combinationName1));

        var result = counter.countResult(combinations, bet);

        Assertions.assertEquals(bet * config.symbols().get(symbol).rewardMultiplier() * config.winCombinations().get(combinationName0).rewardMultiplier() * config.winCombinations().get(combinationName1).rewardMultiplier(),
                result);
    }

    @Test
    void countResultMultipleSymbolsTest() {
        var combinationName0 = "same_symbol_3_times";
        var combinationName1 = "same_symbols_horizontally";
        var bet = 100;
        var symbol0 = "A";
        var symbol1 = "B";
        var combinations = Map.of(symbol0, List.of(combinationName0), symbol1, List.of(combinationName1));

        var result = counter.countResult(combinations, bet);

        Assertions.assertEquals(bet * config.symbols().get(symbol0).rewardMultiplier() * config.winCombinations().get(combinationName0).rewardMultiplier() + bet * config.symbols().get(symbol1).rewardMultiplier() * config.winCombinations().get(combinationName1).rewardMultiplier(),
                result);
    }
}
