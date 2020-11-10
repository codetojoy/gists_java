
package net.codetojoy;

import org.junit.*;
import org.junit.runner.*;
import static org.junit.Assert.*;

import org.mockito.Mockito;
import static org.mockito.Matchers.*;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.PowerMockIgnore;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
@PrepareForTest({SomeClassTestCase.class, SomeClass.class})
public class SomeClassTestCase {

    // there is no need to mock the class, so just use it:
    private SomeClass someClass = new SomeClass();

    @Test(expected=Test.None.class)
    public void testRun() throws Exception{     
        // comment this line and test will take several seconds:
        PowerMockito.mockStatic(Thread.class);
        
        // test
        someClass.run(); 
    }
}
