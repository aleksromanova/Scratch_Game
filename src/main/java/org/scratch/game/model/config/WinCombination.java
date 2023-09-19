package org.scratch.game.model.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Objects;

public record WinCombination(
        @JsonProperty("reward_multiplier")
        int rewardMultiplier,
        WinCombinationWhenType when,
        int count,
        WinCombinationGroup group,
        @JsonProperty("covered_areas")
        String[][] coveredAreas
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WinCombination that = (WinCombination) o;
        return rewardMultiplier == that.rewardMultiplier && count == that.count && when == that.when && group == that.group;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rewardMultiplier, when, count, group);
    }

    @Override
    public String toString() {
        return "WinCombination{" +
                "rewardMultiplier=" + rewardMultiplier +
                ", when=" + when +
                ", count=" + count +
                ", group=" + group +
                ", coveredAreas=" + Arrays.toString(coveredAreas) +
                '}';
    }
}
