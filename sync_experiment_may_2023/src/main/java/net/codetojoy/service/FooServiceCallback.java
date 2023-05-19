
package net.codetojoy.service;

import java.util.function.Consumer;
import java.util.concurrent.*;

public class FooServiceCallback implements Consumer<Boolean> {
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private boolean result;

    @Override 
    public void accept(Boolean ok) {
        if (ok != null && ok) {
            result = true;
        }
        countDownLatch.countDown();
    }

    public boolean isReady() {
        try { 
            countDownLatch.await(10, TimeUnit.SECONDS);
        } catch (Exception ex) {
            System.err.println("caught ex: " + ex);
        }
        return result; 
    }
}
