package net.codetojoy.schedule;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = IncludeAllDayConfig.class, name = "includeAllDay"),
    @JsonSubTypes.Type(value = ExcludeAllDayConfig.class, name = "excludeAllDay"),
    @JsonSubTypes.Type(value = CustomRangeConfig.class, name = "customRange")
})
public abstract class DayConfig {
}
