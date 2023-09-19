package org.scratch.game.service.calculator;

import org.scratch.game.model.config.Config;
import org.scratch.game.model.config.WinCombinationWhenType;
import org.scratch.game.service.calculator.check.LinearSymbolWinCombinationChecker;
import org.scratch.game.service.calculator.check.SameSymbolWinCombinationChecker;

import java.util.*;
import java.util.stream.Stream;

/**
 * Analyzing win combinations in the matrix
 */
public class CombinationsAnalyzer {

    final Config config;

    public CombinationsAnalyzer(Config config) {
        this.config = config;
    }

    /**
     * Analyze matrix to find all win combinations
     *
     * @param matrix game matrix
     * @return map of symbols and win combinations
     */
    public Map<String, List<String>> findWinCombinations(String[][] matrix) {
        //initialise checkers for different types of combinations
        var checkers = Map.of(WinCombinationWhenType.LINEAR_SYMBOLS, new LinearSymbolWinCombinationChecker(matrix),
                WinCombinationWhenType.SAME_SYMBOLS, new SameSymbolWinCombinationChecker(matrix, config));

        var appliedWinningCombinations = new HashMap<String, List<String>>();
        for (var winCombination : config.winCombinations().entrySet()) {
            checkers.get(winCombination.getValue().when()).check(winCombination.getValue()).forEach(symbol ->
                    appliedWinningCombinations.merge(symbol, List.of(winCombination.getKey()), (l1, l2) -> Stream.of(l1, l2).flatMap(List::stream).toList())
            );
        }
        return appliedWinningCombinations;
    }
}
