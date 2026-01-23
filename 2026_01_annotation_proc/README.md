# Annotation Processing Sandbox

A sandbox project that illustrates annotation processing in Java using reflection.

## Requirements

- Java 21

## Project Structure

```
src/main/java/net/codetojoy/
├── annotation/
│   ├── Foo.java          # Method-level annotation
│   ├── Bar.java          # Method-level annotation
│   └── Restrict.java     # Class-level annotation
├── ExampleController.java    # Sample class with annotations
├── AnnotationProcessor.java  # Reads and reports annotations via reflection
└── Runner.java               # Entry point
```

## Usage

Build and run:

```bash
./gradlew run
```

Expected output:

```
Processing class: net.codetojoy.ExampleController

Class-level annotations:
  @Restrict

Method-level annotations:
  getEmployees():
    @Foo
  getProducts():
    @Bar
```

## Custom Annotations

| Annotation | Target | Description |
|------------|--------|-------------|
| `@Restrict` | Class | Applied at the class level |
| `@Foo` | Method | Applied to `getEmployees()` |
| `@Bar` | Method | Applied to `getProducts()` |

All annotations use `RetentionPolicy.RUNTIME` to enable reflection-based processing.
