package net.codetojoy.sensor.common;

import com.google.inject.Module;

/**
 * Marker interface for sensor Guice modules.
 * Implementations are discovered via Java's ServiceLoader mechanism.
 */
public interface SensorModule extends Module {
}
