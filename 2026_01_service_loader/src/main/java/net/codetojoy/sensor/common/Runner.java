package net.codetojoy.sensor.common;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.List;
import java.util.ServiceLoader;

public class Runner {

    public static void main(String[] args) {
        System.out.println("Sensor Data Application");
        System.out.println("=======================");

        ServiceLoader<SensorModule> moduleLoader = ServiceLoader.load(SensorModule.class);

        SensorModule module = moduleLoader.findFirst()
                .orElseThrow(() -> new RuntimeException("No SensorModule implementation found!"));

        Injector injector = Guice.createInjector(module);
        SensorDataProvider provider = injector.getInstance(SensorDataProvider.class);

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
}
