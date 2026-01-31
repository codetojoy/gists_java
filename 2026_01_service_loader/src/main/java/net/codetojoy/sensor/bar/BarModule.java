package net.codetojoy.sensor.bar;

import com.google.inject.AbstractModule;
import net.codetojoy.sensor.common.SensorDataProvider;
import net.codetojoy.sensor.common.SensorModule;

/**
 * Guice module for Version Bar.
 * Binds SensorDataProvider to BarSensorDataProvider (encrypted data reader).
 */
public class BarModule extends AbstractModule implements SensorModule {

    @Override
    protected void configure() {
        bind(SensorDataProvider.class).to(BarSensorDataProvider.class);
    }
}
