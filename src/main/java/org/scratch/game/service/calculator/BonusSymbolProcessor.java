package org.scratch.game.service.calculator;

import org.scratch.game.model.config.Config;
import org.scratch.game.model.config.SymbolType;

public class BonusSymbolProcessor {
    private final Config config;

    public BonusSymbolProcessor(Config config) {
        this.config = config;
    }

    public String findBonusSymbol(String[][] matrix) {
        for (var i = 0; i < config.rows(); i++) {
            for (var j = 0; j < config.columns(); j++) {
                if (config.symbols().get(matrix[i][j]).type() == SymbolType.BONUS) {
                    return matrix[i][j];
                }
            }
        }
        return null;
    }

    public double applyBonusSymbol(String bonusSymbol, double reward) {
        var symbol = config.symbols().get(bonusSymbol);
        if (symbol != null) {
            return switch (symbol.impact()) {
                case EXTRA_BONUS -> reward + symbol.extra();
                case MULTIPLY_REWARD -> reward * symbol.rewardMultiplier();
                case MISS -> reward;
            };
        }
        return reward;
    }

    public boolean isBonusApplicable(String bonusSymbol) {
        var symbol = config.symbols().get(bonusSymbol);
        if (symbol != null) {
            return switch (symbol.impact()) {
                case EXTRA_BONUS, MULTIPLY_REWARD -> true;
                case MISS -> false;
            };
        }
        return false;
    }
}