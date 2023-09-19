package org.scratch.game.service.calculator.check;

import org.scratch.game.model.config.WinCombination;

import java.util.*;

public class LinearSymbolWinCombinationChecker implements WinCombinationChecker {
    private final String[][] matrix;

    public LinearSymbolWinCombinationChecker(String[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> check(WinCombination combination) {
        var symbols = new ArrayList<String>();
        for (var coveredArea : combination.coveredAreas()) {
            String symbol = null;
            var combinationApplied = true;
            for (var coordinatesString : coveredArea) {
                var coordinates = Arrays.stream(coordinatesString.split(":")).map(Integer::parseInt).toArray(Integer[]::new);
                if (symbol == null) {
                    symbol = matrix[coordinates[0]][coordinates[1]];
                } else if (!symbol.equals(matrix[coordinates[0]][coordinates[1]])) {
                    combinationApplied = false;
                    break;
                }
            }
            if (combinationApplied) {
                symbols.add(symbol);
            }
        }
        return symbols;
    }
}
