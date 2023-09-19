package org.scratch.game.service.calculator;

import org.scratch.game.model.config.Config;

import java.util.List;
import java.util.Map;

public class CombinationsRewardCounter {
    private final Config config;

    public CombinationsRewardCounter(Config config) {
        this.config = config;
    }

    public double countResult(Map<String, List<String>> appliedWinningCombinations, double betAmount) {
        if (appliedWinningCombinations == null) {
            return 0;
        } else {
            var reward = 0d;
            for (var symbolCombinations : appliedWinningCombinations.entrySet()) {
                var symbolReward = betAmount * config.symbols().get(symbolCombinations.getKey()).rewardMultiplier();
                for (var combination : symbolCombinations.getValue()) {
                    symbolReward = symbolReward * config.winCombinations().get(combination).rewardMultiplier();
                }
                reward = reward + symbolReward;
            }
            return reward;
        }
    }
}
