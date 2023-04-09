package net.codetojoy;

import net.codetojoy.service.Foo;
import net.codetojoy.service.FooService;

public class App {
    private final FooService fooService = new FooService();

    private void callFooService() {
        String name = "EVH";
        String address = "Pasadena";
        Foo foo = fooService.doSomething(name, address);
        System.out.println("TRACER received foo id: " + foo.getId());
    }

    public static void main(String... args) {
        App app = new App();

        app.callFooService();

        System.out.println("TRACER Ready.");
    }
}
