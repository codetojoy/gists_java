
package net.codetojoy;

import org.junit.*;
import org.junit.runner.*;
import static org.junit.Assert.*;

import org.mockito.Mockito;
import org.mockito.Matchers;
import static org.mockito.Matchers.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.PowerMockIgnore;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
@PrepareForTest({Thread.class}) 
public class ThreadTestCase {

    @Test(expected=Test.None.class)
    public void testThread() throws Exception {     
        PowerMockito.mockStatic(Thread.class);
        PowerMockito.doThrow(new RuntimeException("mock error")).when(Thread.class);
        Thread.sleep(Matchers.anyLong());

        // test
        try {
            Thread.currentThread().sleep(1000L);
            System.out.println("TRACER run OK");
        } catch (Exception ex) {
            System.err.println("TRACER caught message: " + ex.getMessage());
        }

        // PowerMockito.verifyStatic();
    }
}
