
package net.codetojoy;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class StatusTestCase {

    @Test
    public void testBasic() {
        int value = 1 + 2 + 4 + 8;

        // test
        String result = new Status(value).toString();

        assertEquals("f1,f2,f4,f8,", result);
    }
}
