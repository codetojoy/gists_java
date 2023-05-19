
package net.codetojoy;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

class Service {
    List<String> fetch() { 
        long result = System.currentTimeMillis();
        long delay = result % 2000;
        try { Thread.sleep(delay); } catch (Exception ex) {}

        return List.of();
    }

    List<String> absurdFetch() { 
        try {
            Thread.sleep(70_000L);
        } catch(Exception ex) {
        }

        return List.of();
    }
}

public class DemoTestCase {
    private Service service = new Service();

    @Test
    public void testFetch() {
        for (int i = 1; i <= 10; i++) {
            Timer timer = new Timer();
            var result = service.fetch();
            System.out.println("INFO " + new Date().toString() + " random" + i);
            System.out.println(timer.getElapsed("INFO " + new Date().toString() + " fetch " + (System.currentTimeMillis() % 100)  ));
        }
    }

    // @Test
    public void testAbsurdFetch() {
        Timer timer = new Timer();

        var result = service.absurdFetch();
        
        if (timer.exceedsThreshold()) {
            System.out.println(timer.getElapsed("WARN " + new Date().toString() + "  absurdFetch"));
        }
    }
}
