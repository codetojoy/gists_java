
package net.codetojoy;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SomeClass extends Thread {
    private long shortInterval = 100;
    private long longInterval = 5000;

    @Override
    public void run() {
        // original code had `while (true)` but this is simpler as an illustration:
        int counter = 0;
        while (counter < 3) {
            try {
                // clear interruption
                interrupted(); 
                
                long noOfRec = 0;
                if (noOfRec > 0) {
                    Thread.sleep(shortInterval);
                } else {
                    // in addition to Thread.sleep, log the elapsed time 
                    long startTime = System.nanoTime();    
                    Thread.sleep(longInterval);
                    long elapsedInNanos = System.nanoTime() - startTime;
                    long elapsedInSeconds = TimeUnit.SECONDS.convert(elapsedInNanos, TimeUnit.NANOSECONDS);
                    System.out.println(String.format("TRACER elapsed in seconds: %d", elapsedInSeconds));
                }
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt(); 
            } catch (Exception ex) {
                System.out.println("TRACER caught exception: " + ex.getMessage());
            }

            counter++;
        }
    }
}
