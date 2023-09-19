package org.scratch.game.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record Probabilities(
        @JsonProperty("standard_symbols")
        List<StandardSymbolProbability> standardSymbols,
        @JsonProperty("bonus_symbols")
        BonusSymbolProbability bonusSymbols) {
}
