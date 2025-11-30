package net.codetojoy.schedule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Schedule {
    @JsonProperty("sunday")
    private Day sunday;

    @JsonProperty("saturday")
    private Day saturday;

    @JsonProperty("weekDay")
    private Day weekDay;

    public Day getSunday() {
        return sunday;
    }

    public void setSunday(Day sunday) {
        this.sunday = sunday;
    }

    public Day getSaturday() {
        return saturday;
    }

    public void setSaturday(Day saturday) {
        this.saturday = saturday;
    }

    public Day getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Day weekDay) {
        this.weekDay = weekDay;
    }
}
