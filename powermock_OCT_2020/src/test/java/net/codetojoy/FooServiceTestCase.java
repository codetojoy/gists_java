
package net.codetojoy;

import org.junit.*;
import org.junit.runner.*;
import static org.junit.Assert.*;

import org.mockito.Mockito;
import static org.mockito.Matchers.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;

// see https://github.com/powermock/powermock/wiki/Mockito

@RunWith(PowerMockRunner.class)
@PrepareForTest(FooDAO.class)
public class FooServiceTestCase {
    private FooService fooService = null;

	@Before
	public void setUp() {
        PowerMockito.mockStatic(FooDAO.class);
    }

    @Test
    public void testCanary() {
        assertEquals(4, 2+2);
    }

    @Test
    public void testGetFoo_evenId() {
        int id = 5150;
        Foo foo = new Foo();
        foo.id = id;

        PowerMockito.when(FooDAO.getFoo(eq(id))).thenReturn(foo);
        fooService = new FooService();

        // test
        Foo result = fooService.getFoo(id);        

        System.out.println("TRACER result: " + result);
        assertNotNull(result);
        assertTrue(result instanceof Foo);
        assertFalse(result instanceof Bar);
        assertEquals((int) id, (int) result.id);
    }
}
