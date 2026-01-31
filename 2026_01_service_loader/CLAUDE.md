# CLAUDE.md

## Project Summary

Java ServiceLoader demo with two distribution variants (Foo and Bar). Single module with switchable build files.

## Build System

- **Tool**: sbt 1.9.7 with sbt-assembly plugin
- **Java**: 21
- **Structure**: Single module, switchable build templates

## Key Commands

```bash
./build.sh foo       # Build Foo jar (no dependencies, 6KB)
./build.sh bar       # Build Bar jar (includes Bouncy Castle, 7.6MB)
./build.sh clean     # Clean all build artifacts
./build.sh encrypt   # Generate encrypted data file
```

## How Build Works

1. `build.sh` copies `foo-build.sbt.template` or `bar-build.sbt.template` to `build.sbt`
2. Copies appropriate `services-foo/` or `services-bar/` to `META-INF/services/`
3. Runs `sbt clean assembly`
4. Build templates use `excludeFilter` to exclude the other version's code and resources

## Key Files

| File | Purpose |
|------|---------|
| `src/.../common/Runner.java` | Main entry point, uses ServiceLoader |
| `src/.../common/SensorDataProvider.java` | Service interface |
| `src/.../common/SensorReading.java` | Domain record (timestamp, wattHours) |
| `src/.../foo/FooSensorDataProvider.java` | Plain text file reader |
| `src/.../bar/BarSensorDataProvider.java` | Encrypted file reader (AES/CBC/PKCS7) |
| `src/.../bar/EncryptionUtil.java` | CLI tool to encrypt data files |
| `foo-build.sbt.template` | Build config excluding bar package |
| `bar-build.sbt.template` | Build config excluding foo package + BC dependency |
| `build.sh` | Driver script |

## Encryption Details (Version Bar)

- Algorithm: AES/CBC/PKCS7Padding via Bouncy Castle
- Key: `SensorDataKey123` (16 bytes, hardcoded for demo)
- IV: `InitVector123456` (16 bytes, hardcoded for demo)
- Format: Base64-encoded ciphertext

## Data Format

Plain text sensor data (CSV):
```
# Comments start with #
2025-01-15T00:00:00,120.5
```
Fields: ISO timestamp, watt-hours (double)

## Output Jars

- `target/sensor-app-foo.jar` (~6 KB)
- `target/sensor-app-bar.jar` (~7.6 MB, includes Bouncy Castle)

## Package Structure

```
net.codetojoy.sensor.common   # Shared (always included)
net.codetojoy.sensor.foo      # Foo only (excluded in Bar build)
net.codetojoy.sensor.bar      # Bar only (excluded in Foo build)
```
