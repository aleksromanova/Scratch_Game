package org.scratch.game.service.calculator.check;

import org.scratch.game.model.config.Config;
import org.scratch.game.model.config.SymbolType;
import org.scratch.game.model.config.WinCombination;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SameSymbolWinCombinationChecker implements WinCombinationChecker {
    final Map<String, Integer> standardSymbolsCount;

    //preprocessing is done in constructor for optimisation, in order to not go through matrix for checking of each combination
    public SameSymbolWinCombinationChecker (String[][] matrix, Config config) {
        this.standardSymbolsCount = config.symbols().entrySet().stream().filter(entry -> entry.getValue().type() == SymbolType.STANDARD)
                .collect(Collectors.toMap(Map.Entry::getKey, e -> 0));
        for (var i = 0; i < matrix.length; i++) {
            for (var j = 0; j < matrix[0].length; j++) {
                standardSymbolsCount.merge(matrix[i][j], 1, Integer::sum);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> check(WinCombination combination) {
        return standardSymbolsCount.entrySet().stream()
                .filter(entry -> entry.getValue() == combination.count())
                .map(Map.Entry::getKey)
                .toList();
    }
}
