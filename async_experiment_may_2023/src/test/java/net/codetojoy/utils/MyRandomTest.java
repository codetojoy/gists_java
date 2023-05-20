package net.codetojoy.utils;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;

public class MyRandomTest {
    private MyRandom myRandom = new MyRandom();

    @Test
    public void testGetRandomInclusive() {
        int min = 1;
        int max = 3;
        Set<Integer> results = new HashSet<>();

        // test
        int numIterations = 200;
        for (var i = 0; i < numIterations; i++) {
            int result = myRandom.getRandomInclusive(min, max);
            results.add(result);
        }

        assertEquals(3, results.size());
        assertTrue(results.contains(1));
        assertTrue(results.contains(2));
        assertTrue(results.contains(3));
    }
}
