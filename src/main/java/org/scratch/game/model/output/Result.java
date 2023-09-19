package org.scratch.game.model.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Game result
 *
 * @param matrix generated matrix
 * @param reward reward amount
 * @param appliedWinningCombinations applied winning combinations if any
 * @param bonusSymbol applied bonus symbol if applicable
 */
public record Result(
        String[][] matrix,
        double reward,
        @JsonProperty("applied_winning_combinations")
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        Map<String, List<String>> appliedWinningCombinations,
        @JsonProperty("applied_bonus_symbol")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        String bonusSymbol
) {
        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Result result = (Result) o;
                return Double.compare(reward, result.reward) == 0 && Arrays.deepEquals(matrix, result.matrix) && Objects.equals(appliedWinningCombinations, result.appliedWinningCombinations) && Objects.equals(bonusSymbol, result.bonusSymbol);
        }

        @Override
        public int hashCode() {
                int result = Objects.hash(reward, appliedWinningCombinations, bonusSymbol);
                result = 31 * result + Arrays.deepHashCode(matrix);
                return result;
        }

        @Override
        public String toString() {
                return "Result{" +
                        "matrix=" + Arrays.toString(matrix) +
                        ", reward=" + reward +
                        ", appliedWinningCombinations=" + appliedWinningCombinations +
                        ", bonusSymbol='" + bonusSymbol + '\'' +
                        '}';
        }
}
