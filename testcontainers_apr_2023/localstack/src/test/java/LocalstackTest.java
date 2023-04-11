import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import static org.junit.Assert.*;

public class LocalstackTest {

    @Rule
    public GenericContainer<?> container = new GenericContainer<>(DockerImageName.parse("localstack/localstack:latest")).withExposedPorts(4566);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testCanary() {
        System.out.println("TRACER hello... port: " + container.getMappedPort(4566));
        var isDone = false;
        var counter = 0;
        var max = 60;
        while (!isDone) {
            try {
                System.out.println("TRACER canary i: " + counter);
                Thread.sleep(1000);
            } catch (Exception ex) {
            }
            counter++;
            isDone = (counter >= max);
        }
        assertEquals(4, 2+2);
    }
}
