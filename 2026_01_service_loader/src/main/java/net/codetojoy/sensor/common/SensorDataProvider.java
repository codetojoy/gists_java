package net.codetojoy.sensor.common;

import java.util.List;

/**
 * Service interface for providing sensor readings.
 * Implementations are discovered via Java's ServiceLoader mechanism.
 */
public interface SensorDataProvider {

    /**
     * Returns the name of this provider implementation.
     */
    String getName();

    /**
     * Retrieves all available sensor readings.
     * @return list of hourly energy consumption readings
     */
    List<SensorReading> getReadings();
}
