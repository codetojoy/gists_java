
package net.codetojoy;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;

// -----------------
// worker task that waits for 1-5 seconds 

class Task implements Supplier<String> {
    private static int instanceCount = 0;
    private int instanceId = 0;

    Task() {
        instanceCount++;
        instanceId = instanceCount;
    }

    int getRandom() {
        final int min = 1;
        final int max = 5;
        int result = ThreadLocalRandom.current().nextInt(min, max + 1);
        return result;
    }

    @Override
    public String get() {
        String whoAmI = LogUtil.getLogPrefix() + " worker-" + instanceId;

        try {
            int delayInSeconds = getRandom();
            System.out.println(whoAmI + " sleeping (" + delayInSeconds + " sec)");
            TimeUnit.SECONDS.sleep(delayInSeconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        return whoAmI + " done";
    }
}
