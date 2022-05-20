
package net.codetojoy;

import java.time.Duration;

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
        // we don't want the usual try-catch here because the InterruptedException is desirable!!!
        Thread.sleep(Duration.ofSeconds(delayInSeconds)); 
    }

    public static void main(String... args) throws Exception {
        Thread t1 = new Thread(Runner::doWork);
        t1.start();
        doSleep(1);
        t1.interrupt();
        doSleep(4);
        System.out.println("TRACER main Ready.");
    }
}
