package net.codetojoy;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class StringsTest {

    @Test
    public void testAddAsInts_basic() {
        
        // test 
        String result = new Strings().addAsInts("5000", "150");
      
        assertEquals("5150", result);
    }
}
