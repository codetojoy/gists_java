package net.codetojoy.sensor.common;

import java.time.LocalDateTime;

/**
 * Represents an hourly energy consumption reading from a sensor.
 * @param timestamp the hour of the reading
 * @param wattHours energy consumption in watt-hours
 */
public record SensorReading(LocalDateTime timestamp, double wattHours) {

    @Override
    public String toString() {
        return String.format("SensorReading[%s, %.2f Wh]", timestamp, wattHours);
    }
}
