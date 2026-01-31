package net.codetojoy.sensor.common;

import java.util.List;
import java.util.ServiceLoader;

public class Runner {

    public static void main(String[] args) {
        System.out.println("Sensor Data Application");
        System.out.println("=======================");

        ServiceLoader<SensorDataProvider> loader = ServiceLoader.load(SensorDataProvider.class);

        boolean foundProvider = false;
        for (SensorDataProvider provider : loader) {
            foundProvider = true;
            System.out.println("\nUsing provider: " + provider.getName());
            System.out.println("Readings:");

            List<SensorReading> readings = provider.getReadings();
            for (SensorReading reading : readings) {
                System.out.println("  " + reading);
            }

            System.out.println("\nTotal readings: " + readings.size());
            double total = readings.stream().mapToDouble(SensorReading::wattHours).sum();
            System.out.printf("Total consumption: %.2f Wh%n", total);
        }

        if (!foundProvider) {
            System.out.println("No SensorDataProvider implementations found!");
        }
    }
}
