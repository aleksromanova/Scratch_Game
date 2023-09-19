package org.scratch.game.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum WinCombinationGroup {
    @JsonProperty("same_symbols")
    SAME_SYMBOLS,
    @JsonProperty("horizontally_linear_symbols")
    HORIZONTALLY_LINEAR_SYMBOLS,
    @JsonProperty("vertically_linear_symbols")
    VERTICALLY_LINEAR_SYMBOLS,
    @JsonProperty("ltr_diagonally_linear_symbols")
    LTR_DIAGONALLY_LINEAR_SYMBOLS,
    @JsonProperty("rtl_diagonally_linear_symbols")
    RTR_DIAGONALLY_LINEAR_SYMBOLS
}
