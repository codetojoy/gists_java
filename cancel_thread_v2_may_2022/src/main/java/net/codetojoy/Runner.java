
package net.codetojoy;

import java.time.Duration;
import java.util.concurrent.*;

public class Runner {
    static void doWork() {
        try {
            int maxLoops = 20;
            for (int i = 0; i < maxLoops; i++) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("TRACER thread interrupted!");
                    break;
                }
                System.out.println("TRACER working t: " + Thread.currentThread());
                doSleep(3);
            }
        } catch (Exception ex) {
            System.err.println("TRACER caught ex: " + ex.getMessage());
        }
    }

    static void doSleep(long delayInSeconds) throws Exception {
        // we don't want the usual try-catch here 
        // because the InterruptedException is desirable!!!
        Thread.sleep(Duration.ofSeconds(delayInSeconds)); 
    }

    public static void main(String... args) throws Exception {
        var executor = Executors.newFixedThreadPool(3);
        var future = executor.submit(Runner::doWork);
        doSleep(1);
        future.cancel(true);
        doSleep(4);
        System.out.println("TRACER main Ready.");
    }
}
