package net.codetojoy.sensor.foo;

import net.codetojoy.sensor.common.SensorDataProvider;
import net.codetojoy.sensor.common.SensorReading;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Version Foo implementation of SensorDataProvider.
 * Reads sensor data from a plain text file bundled in the jar.
 */
public class FooSensorDataProvider implements SensorDataProvider {

    private static final String DATA_FILE = "/data/sensor_data.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public String getName() {
        return "FooSensorDataProvider";
    }

    @Override
    public List<SensorReading> getReadings() {
        List<SensorReading> readings = new ArrayList<>();

        try (InputStream is = getClass().getResourceAsStream(DATA_FILE);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length == 2) {
                    LocalDateTime timestamp = LocalDateTime.parse(parts[0].trim(), FORMATTER);
                    double wattHours = Double.parseDouble(parts[1].trim());
                    readings.add(new SensorReading(timestamp, wattHours));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read sensor data from " + DATA_FILE, e);
        }

        return readings;
    }
}
