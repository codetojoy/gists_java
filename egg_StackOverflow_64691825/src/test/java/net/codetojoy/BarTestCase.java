
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
@PrepareForTest({Foo.class}) 
public class BarTestCase {

    @Test(expected=Test.None.class)
    public void testBar() throws Exception {     
        PowerMockito.mockStatic(Foo.class);
        PowerMockito.doThrow(new RuntimeException("mock error")).when(Foo.class, "mySleep", Matchers.anyLong());
        Bar myBar = new Bar();

        // test
        try {
            myBar.bar();
            System.out.println("TRACER run OK");
        } catch (Exception ex) {
            System.err.println("TRACER caught message: " + ex.getMessage());
        }

        // PowerMockito.verifyStatic();
    }
}
