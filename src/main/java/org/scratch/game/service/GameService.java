package org.scratch.game.service;

import org.scratch.game.model.output.Result;
import org.scratch.game.service.calculator.ResultCalculator;
import org.scratch.game.service.generator.MatrixGenerator;

public class GameService {
    private final MatrixGenerator generator;
    private final ResultCalculator calculator;

    public GameService(MatrixGenerator generator, ResultCalculator calculator) {
        this.generator = generator;
        this.calculator = calculator;
    }

    public Result play(double betAmount) {
        var matrix = generator.generate();
        return calculator.calculateResult(matrix, betAmount);
    }
}
