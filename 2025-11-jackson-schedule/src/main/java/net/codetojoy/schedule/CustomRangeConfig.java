package net.codetojoy.schedule;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("customRange")
public class CustomRangeConfig extends DayConfig {
    @JsonProperty("customRange")
    private CustomRange customRange;

    public CustomRange getCustomRange() {
        return customRange;
    }

    public void setCustomRange(CustomRange customRange) {
        this.customRange = customRange;
    }
}
