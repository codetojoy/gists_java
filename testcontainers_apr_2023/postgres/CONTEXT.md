# Testcontainers + JUnit 5 Project

## Project Overview

This is a Java project demonstrating the use of Testcontainers with JUnit 5 for integration testing with PostgreSQL.

### Technology Stack
- **Build Tool**: Gradle
- **Java Version**: Java 21 (Amazon Corretto)
- **Test Framework**: JUnit 5 (Jupiter) 5.10.0
- **Testcontainers**: 1.21.3
- **Logging**: Logback 1.3.5

### Dependencies (build.gradle:10-15)
```gradle
testImplementation 'org.junit.jupiter:junit-jupiter:5.10.0'
testImplementation 'org.testcontainers:testcontainers:1.21.3'
testImplementation 'org.testcontainers:junit-jupiter:1.21.3'
testImplementation 'ch.qos.logback:logback-classic:1.3.5'
```

## Issue Resolved: JUnit 4/5 Annotation Mismatch

### Problem
The test was failing with `AssertionError` because `container.isRunning()` returned `false`. Docker was confirmed to be running, and a similar JUnit 4 example worked fine.

### Root Cause
The test class mixed JUnit 4 and JUnit 5 annotations:
- **JUnit 4**: `@Test` from `org.junit.Test`
- **JUnit 5**: `@Testcontainers` and `@Container` from `org.testcontainers.junit.jupiter`

When JUnit 4's test runner executed the test, it didn't recognize the JUnit 5 Testcontainers annotations (`@Testcontainers` and `@Container`), so the container was never started.

### Solution
Updated `src/test/java/BasicTest.java` to use JUnit 5 annotations consistently:

**Changed imports:**
```java
// Before (JUnit 4)
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.*;

// After (JUnit 5)
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
```

### Test Structure

The test class (`BasicTest.java`) demonstrates:
1. **@Testcontainers** - JUnit 5 extension to manage container lifecycle
2. **@Container** - Marks the container field for automatic management
3. **GenericContainer** - Creates a PostgreSQL container with environment variable
4. **testCanary()** - Verifies the container is running and outputs container name

### Key Files
- `build.gradle` - Build configuration with dependencies
- `src/test/java/BasicTest.java` - Main test class
- `src/test/resources/logback-test.xml` - Logging configuration for Testcontainers
- `test.sh` - Test execution script
- `compile.sh` - Compilation script

### Important Notes
- The `@Testcontainers` and `@Container` annotations from the `junit.jupiter` package ONLY work with JUnit 5
- For JUnit 4, you would need to use `@Rule` or `@ClassRule` instead
- Always ensure test annotations match the JUnit version configured in your build file

### Build Commands
```bash
./gradlew clean test    # Run tests
./test.sh              # Run tests via script
./compile.sh           # Compile only
```

### Test Output
When successful, the test outputs:
```
TRACER container name: <container-name>
BUILD SUCCESSFUL
```
