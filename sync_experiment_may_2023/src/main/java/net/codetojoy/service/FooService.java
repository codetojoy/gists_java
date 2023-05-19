
package net.codetojoy.service;

import net.codetojoy.Timer;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class FooService {
    private ExecutorService executor = Executors.newFixedThreadPool(3);

    public void doSomething(String name, String address, Consumer<Boolean>... callback) {
        try {
            Consumer<Boolean> fooServiceCallback = null;
            if (callback != null && callback.length > 0) {
                fooServiceCallback = callback[0];
            }
            FooTask fooTask = new FooTask(name, address, fooServiceCallback);
            executor.submit(fooTask);
        } catch (Exception ex) {
            System.err.println("TRACER caught ex: " + ex.getMessage());
        }
    }
}
