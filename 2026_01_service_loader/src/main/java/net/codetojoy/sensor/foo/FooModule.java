package net.codetojoy.sensor.foo;

import com.google.inject.AbstractModule;
import net.codetojoy.sensor.common.SensorDataProvider;
import net.codetojoy.sensor.common.SensorModule;

/**
 * Guice module for Version Foo.
 * Binds SensorDataProvider to FooSensorDataProvider.
 */
public class FooModule extends AbstractModule implements SensorModule {

    @Override
    protected void configure() {
        bind(SensorDataProvider.class).to(FooSensorDataProvider.class);
    }
}
