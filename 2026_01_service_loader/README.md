# Sensor Data Application

A sandbox project demonstrating Java's `ServiceLoader` mechanism for plugin architectures.

## Overview

This project shows how to create two distributions of an application that share a common interface but have different implementations:

- **Version Foo**: Reads sensor data from a plain text file (no external dependencies)
- **Version Bar**: Reads sensor data from an AES-encrypted file using Bouncy Castle

The key insight is that Version Foo's jar does not include Bouncy Castle (6 KB), while Version Bar's jar bundles it (7.6 MB).

## Requirements

- Java 21
- sbt 1.9+

## Project Structure

```
├── src/main/java/net/codetojoy/sensor/
│   ├── common/          # Shared: Runner, SensorDataProvider, SensorReading
│   ├── foo/             # Version Foo: FooSensorDataProvider
│   └── bar/             # Version Bar: BarSensorDataProvider, EncryptionUtil
├── src/main/resources/
│   ├── data/            # Data files (plain and encrypted)
│   └── META-INF/
│       ├── services-foo/   # ServiceLoader config for Foo
│       └── services-bar/   # ServiceLoader config for Bar
├── foo-build.sbt.template  # Build config for Version Foo
├── bar-build.sbt.template  # Build config for Version Bar
└── build.sh                # Driver script for building
```

## Building

Build Version Foo (no Bouncy Castle):
```bash
./build.sh foo
# Output: target/sensor-app-foo.jar
```

Build Version Bar (with Bouncy Castle):
```bash
./build.sh bar
# Output: target/sensor-app-bar.jar
```

Clean build artifacts:
```bash
./build.sh clean
```

Generate encrypted data file:
```bash
./build.sh encrypt
```

## Running

```bash
java -jar target/sensor-app-foo.jar
java -jar target/sensor-app-bar.jar
```

Both versions produce identical output but use different data providers discovered via `ServiceLoader`.

## How ServiceLoader Works

1. Define a service interface (`SensorDataProvider`)
2. Create implementations in separate packages (`foo`, `bar`)
3. Register implementations in `META-INF/services/<fully-qualified-interface-name>`
4. At runtime, `ServiceLoader.load(SensorDataProvider.class)` discovers all registered implementations

The build script copies the appropriate ServiceLoader config and excludes the other version's code.

## Package Structure

- `net.codetojoy.sensor.common` - Shared code (Runner, interface, domain objects)
- `net.codetojoy.sensor.foo` - Version Foo implementation
- `net.codetojoy.sensor.bar` - Version Bar implementation
