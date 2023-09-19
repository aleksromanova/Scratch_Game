package org.scratch.game.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Scratch Game configuration
 *
 * @param columns number of columns in matrix
 * @param rows number of rows in matrix
 * @param symbols possible symbols
 * @param probabilities symbols probabilities
 * @param winCombinations win combinations
 */
public record Config(int columns, int rows, Map<String, Symbol> symbols, Probabilities probabilities,
                     @JsonProperty("win_combinations") Map<String, WinCombination> winCombinations) {
}
