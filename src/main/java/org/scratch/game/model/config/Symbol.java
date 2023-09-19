package org.scratch.game.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Symbol(
        @JsonProperty("reward_multiplier")
        double rewardMultiplier,
        SymbolType type,
        BonusImpact impact,
        double extra
) {
}
