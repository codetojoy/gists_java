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
        emitLog("setting latch for sync operation");
        CountDownLatch latch = new CountDownLatch(1);
        fooService.doSomething(name, address, new Consumer<Boolean>() {
            @Override
            public void accept(Boolean result) {
                emitLog("received result: " + result);
                latch.countDown();
            }
        });
        latch.await();
        emitLog("latch is ready!");
    }

    public static void main(String... args) throws Exception {
        App app = new App();

        app.callFooService();

        System.out.println("TRACER Ready.");
    }
}
