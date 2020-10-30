
package net.codetojoy;

import org.junit.*;
import org.junit.runner.*;
import static org.junit.Assert.*;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;
// import static org.mockito.Matchers.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

// see https://github.com/powermock/powermock/wiki/Mockito

@RunWith(PowerMockRunner.class)
@PrepareForTest(LoggerFact.class)
public class MyClassTestCase {
    private MyClass myClass = null;

	@Before
	public void setUp() {
        PowerMockito.mockStatic(LoggerFact.class);
    }

    @Test
    public void testCanary() {
        assertEquals(4, 2+2);
    }

    @Test
    public void testGetFoo_evenId() {
        Logger mockLogger = Mockito.mock(Logger.class);

        PowerMockito.when(LoggerFact.getLogger(eq(MyClass.class),eq("test"))).thenReturn(mockLogger);
        myClass = new MyClass();

        // test
        myClass.methodToBeTested();

        // verify(mockLogger, times(1)).info(any());
        verify(mockLogger, times(1)).info(eq("This is a test"));
    }
}
