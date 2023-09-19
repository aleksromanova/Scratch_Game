package org.scratch.game.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum WinCombinationWhenType {
    @JsonProperty("same_symbols")
    SAME_SYMBOLS,
    @JsonProperty("linear_symbols")
    LINEAR_SYMBOLS
}
