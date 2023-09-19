package org.scratch.game.service.generator;

import org.scratch.game.model.config.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RandomMatrixGenerator implements MatrixGenerator {
    private final Config config;
    private final Random random = new Random();
    public RandomMatrixGenerator(Config config) {
        this.config = config;
    }

    /**
     * Generates matrix according to config
     *
     * @return matrix of symbols
     */
    public String[][] generate() {
        var result = new String[config.columns()][config.rows()];
        //fill matrix with standard symbols
        for (var i = 0; i < config.rows(); i++) {
            for (var j = 0; j < config.columns(); j++) {
                var probabilityArray = generateStandardSymbolProbabilityArray(i, j);
                result[i][j] = probabilityArray.get(random.nextInt(probabilityArray.size()));
            }
        }
        //add one bonus symbol
        var i = random.nextInt(config.rows());
        var j = random.nextInt(config.columns());
        var probabilityArray = generateBonusSymbolProbabilityArray();
        result[i][j] = probabilityArray.get(random.nextInt(probabilityArray.size()));
        return result;
    }

    private List<String> generateStandardSymbolProbabilityArray(int row, int column) {
        var result = new ArrayList<String>();
        var symbolsProbability = config.probabilities().standardSymbols().stream()
                .filter(p -> p.column() == column)
                .filter(p -> p.row() == row)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Probability is not found"))
                .symbols();
        for (var symbolProbability : symbolsProbability.entrySet()) {
            for (var i = 0; i < symbolProbability.getValue(); i++) {
                result.add(symbolProbability.getKey());
            }
        }
        return result;
    }

    private List<String> generateBonusSymbolProbabilityArray() {
        var result = new ArrayList<String>();
        for (var symbolProbability : config.probabilities().bonusSymbols().symbols().entrySet()) {
            for (var i = 0; i < symbolProbability.getValue(); i++) {
                result.add(symbolProbability.getKey());
            }
        }
        return result;
    }
}
