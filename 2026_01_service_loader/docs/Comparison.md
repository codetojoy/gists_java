# ServiceLoader vs Guice for Plugin Architectures

Both can achieve plugin architectures, but they solve different problems.

## Comparison

| Aspect | ServiceLoader | Guice |
|--------|---------------|-------|
| **Discovery** | Automatic at runtime via `META-INF/services` | Must explicitly install modules |
| **Dependencies** | None (built into JDK) | Requires Guice jar |
| **Classpath isolation** | Plugin exists or doesn't | Plugin code must compile |
| **Configuration** | Zero config, just drop jar | Must wire modules in code |

## Why ServiceLoader Wins for This Use Case

The key requirement was:

> *Version Foo should not require the Bouncy Castle jar for compilation or run-time*

With **ServiceLoader**:
- Foo jar simply doesn't include `BarSensorDataProvider.class`
- No code references Bar, so no Bouncy Castle needed
- At runtime, ServiceLoader finds only what's on the classpath

With **Guice**:
```java
// This code must compile, meaning BC must be on classpath
Injector injector = Guice.createInjector(
    new FooModule(),    // OK
    new BarModule()     // Requires BC to compile!
);
```

Even with conditional module loading, the `BarModule` class itself imports Bouncy Castle types, so it must be present at compile time.

## When Guice is Better

- Complex dependency graphs with constructor injection
- Scopes (singleton, request-scoped, etc.)
- AOP/interceptors
- When all plugins are always present, just need flexible wiring

## Summary

ServiceLoader is more powerful for **true plugin isolation** where plugins may not even exist on the classpath. Guice is better for **dependency injection** within an application where all code is present.
