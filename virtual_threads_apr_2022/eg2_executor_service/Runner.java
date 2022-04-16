
// https://download.java.net/java/early_access/loom/docs/api/java.base/java/util/concurrent/Executors.html#newVirtualThreadPerTaskExecutor()

import java.util.concurrent.*;
import java.util.*;

class MyTask implements Runnable {
    int id;

    MyTask(int id) {
        this.id = id;
    }

    @Override 
    public void run() {
        try {
            long delayInMillis = id * 1000;
            System.out.println("TRACER id: " + id + " sleeping...");
            Thread.sleep(delayInMillis);
        } catch(Exception ex) {
        }
    }
}

public class Runner {
    void run() throws Exception {
        var executorService = Executors.newVirtualThreadPerTaskExecutor();
        var task1 = new MyTask(1);
        var task2 = new MyTask(2);
        var task3 = new MyTask(3);
        executorService.submit(task1);
        executorService.submit(task2);
        executorService.submit(task3);
        executorService.shutdown(); // Disable new tasks from being submitted
        System.out.println("TRACER shutting down ...");
        executorService.awaitTermination(5, TimeUnit.SECONDS);
    }

    static public void main(String... args) {
        try {
            new Runner().run();
            System.out.println("Ready.");
        } catch (Exception ex) {
            System.err.println("caught ex");
        }
    }
}
