# Claude Code Guidelines

## Project Overview

Java annotation processing sandbox using reflection. Demonstrates custom annotations and runtime processing.

## Build Commands

- `./gradlew build` - Compile the project
- `./gradlew run` - Run the application
- `./gradlew test` - Run tests
- `./gradlew clean` - Clean build artifacts

## Code Conventions

- Java 21
- Package: `net.codetojoy`
- Annotations in: `net.codetojoy.annotation`

## Key Files

- `AnnotationProcessor.java` - Core logic for reading annotations via reflection
- `ExampleController.java` - Sample class demonstrating annotation usage
- `Runner.java` - Application entry point

## Architecture Notes

- All custom annotations use `RetentionPolicy.RUNTIME` for reflection access
- `@Restrict` targets classes (`ElementType.TYPE`)
- `@Foo` and `@Bar` target methods (`ElementType.METHOD`)
