package org.scratch.game.service.calculator;

import org.scratch.game.model.output.Result;

public interface ResultCalculator {
    Result calculateResult(String[][] matrix, double betAmount);
}
