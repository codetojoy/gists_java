package net.codetojoy.schedule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Day {
    @JsonProperty("config")
    private DayConfig config;

    public DayConfig getConfig() {
        return config;
    }

    public void setConfig(DayConfig config) {
        this.config = config;
    }
}
