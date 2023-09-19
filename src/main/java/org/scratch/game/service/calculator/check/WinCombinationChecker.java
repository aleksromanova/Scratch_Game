package org.scratch.game.service.calculator.check;

import org.scratch.game.model.config.WinCombination;

import java.util.List;

public interface WinCombinationChecker {
    /**
     * Check if win combination is applicable
     *
     * @param combination win combination for checking
     * @return list of symbols for which combination is applicable
     */
    List<String> check(WinCombination combination);
}
