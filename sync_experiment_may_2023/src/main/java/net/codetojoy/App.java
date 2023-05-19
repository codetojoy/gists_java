package net.codetojoy;

import net.codetojoy.service.*;

import java.util.concurrent.*;
import java.util.function.Consumer;

public class App {
    private final FooService fooService = new FooService();

    private void emitLog(String msg) {
        long threadId = Thread.currentThread().getId();
        System.err.println("TRACER tid: " + threadId + " " + msg);
    }

    private void callFooService() throws Exception {
        String name = "EVH";
        String address = "Pasadena";
        emitLog("indirectly setting latch for sync operation");
        FooServiceCallback fooServiceCallback = new FooServiceCallback();
        fooService.doSomething(name, address, fooServiceCallback);
        boolean isReady = fooServiceCallback.isReady();
        emitLog("indirect: latch is ready! isReady: " + isReady);
    }

    public static void main(String... args) throws Exception {
        App app = new App();

        app.callFooService();

        System.out.println("TRACER Ready.");
    }
}
