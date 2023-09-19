package org.scratch.game.service.calculator;

import org.scratch.game.model.config.Config;
import org.scratch.game.model.output.Result;

public class ResultCalculatorImpl implements ResultCalculator {
    private final CombinationsAnalyzer analyzer;
    private final CombinationsRewardCounter combinationsRewardCounter;
    private final BonusSymbolProcessor bonusSymbolProcessor;

    public ResultCalculatorImpl(Config config) {
        analyzer = new CombinationsAnalyzer(config);
        combinationsRewardCounter = new CombinationsRewardCounter(config);
        bonusSymbolProcessor = new BonusSymbolProcessor(config);
    }

    public Result calculateResult(String[][] matrix, double betAmount) {
        var appliedWinningCombinations = analyzer.findWinCombinations(matrix);
        if (appliedWinningCombinations == null || appliedWinningCombinations.isEmpty()) {
            return new Result(matrix, 0.0, appliedWinningCombinations, null);
        } else {
            var reward = combinationsRewardCounter.countResult(appliedWinningCombinations, betAmount);
            var bonusSymbol = bonusSymbolProcessor.findBonusSymbol(matrix);
            if (bonusSymbolProcessor.isBonusApplicable(bonusSymbol)) {
                reward = bonusSymbolProcessor.applyBonusSymbol(bonusSymbol, reward);
                return new Result(matrix, reward, appliedWinningCombinations, bonusSymbol);
            }
            return new Result(matrix, reward, appliedWinningCombinations, null);
        }
    }
}
