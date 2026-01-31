
### Summary

Create a small sandbox application that illustrates the concept of Java's "service loader".

We want to create two version of an app: Version Foo and Version Bar with these traits:

* Both Version Foo and Version Bar should use the same interface to query sensor readings for hourly energy consumption (in watt-hours).
* Version Foo should have a plug-in implementation of the interface that reads mock data from a file.
* Version Bar should have its own plug-in implementation, where it reads mock data from a file, but where the file is encrypted, using a Bouncy Castle jar for the encryption.
* We want to use Java's service loader so that we can create two distributions of the app:
    * Version Foo of the app should not require the Bouncy Castle jar for compilation or run-time.
    * Version Bar of the app should include the Bouncy Castle jar (clearly).
    * The idea is that versions would ship with the appropriate plugins.
* Technical specs:
    * use Java 21
    * use sbt as the build tool (possibly with separate files for Foo and Bar)
    * build the app as a standalone jar (for now)

### COMPLETE - Phase 1: TODO list

* COMPLETE - 1. create basic scaffolding for project:
    * Java package is `net.codetojoy.sensor`
    * simple `Runner.java` as main driver, which prints "Hello"
    * basic sbt build that packages the app
    * create an interface and related Java objects to query readings from a sensor. The readings are for hourly energy consumption, and units of measurement are watt-hours.
* COMPLETE- 2. introduce Version Foo
    * as described above, implement Version Foo for the app using a plugin architecture (with service loader in Java)
    * alter the build so that the app can be distributed a single jar, which contains some sample data files 

### COMPLETE - Phase 2: TODO list

* COMPLETE - introduce Version Bar 
    * as described above, implement Version Bar for the app using a plugin architecture (with service loader in Java)
    * it is crucial for the demo that Version Bar use a jar that Version Foo does not: e.g. a standard bouncy-castle jar with some token encryption to keep us honest. 
    * alter the build so that there are jar/deliverables for both Version Foo and Version Bar. Ideally, one does not contain jars that are used solely by the other.

### Phase 3: TODO list

* Refactor projects so that it is a single module with packages `net.codetojoy.sensor.common`, `net.codetojoy.sensor.foo`, and `net.codetojoy.sensor.bar` and that the Foo and Bar deliverables are build with `foo-build.sbt` and `bar-build.sbt` that are copied into place (i.e. `build.sbt`) based on a driver Bash script.
* The reason for this is that we want to bring this code, later, into a Play Framework web app.

