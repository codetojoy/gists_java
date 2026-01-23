
### Summary

We want a sandbox project that illustrates annotation processing in Java.

That is, a sample class with various (or no) annotations, and then a small program that reads
processes those annotations.

### Phase 1 Goals

* TODO: Create a Gradle project using Java 21 and a Java package of `net.codetojoy`.
* TODO: Create a custom annotation `Foo` in package `net.codetojoy.annotation`.
* TODO: Create a custom annotation `Bar` in package `net.codetojoy.annotation`.
* TODO: Create a custom annotation `Restrict` in package `net.codetojoy.annotation`.
* TODO: Create a file `ExampleController.java` with these properties:
    * Apply `@Restrict` to at the class level.
    * Create a simple method `getEmployees()` and apply `@Foo` at method level.
    * Create a simple method `getProducts()` and apply `@Bar` at method level.
* TODO: Create an annotation processor that can process a given class and report the annotations on both the class-level and method-level.
* TODO: Create a simple `Runner` class that executes the annotation processor on `ExampleController`.

