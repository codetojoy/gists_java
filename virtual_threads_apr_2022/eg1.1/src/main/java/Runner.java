
// from https://javahippie.net/java/concurrency/2022/04/12/getting-started-with-virtual-threads.html

public class Runner {
    void run() throws Exception {
        int numThreads = 20_000;
        for (int i = 0; i < numThreads; i++) {
            final int index = i;
            Thread.ofVirtual().start(() -> System.out.println("TRACER i : " + index));
        }
        Thread.sleep(5_000);
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
