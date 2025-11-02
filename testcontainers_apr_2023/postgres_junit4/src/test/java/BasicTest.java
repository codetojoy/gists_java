import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import static org.junit.Assert.*;

public class BasicTest {
    @Rule
    public final GenericContainer<?> container = new GenericContainer<>(DockerImageName.parse("postgres"))
            .withEnv("POSTGRES_PASSWORD", "test");

    @Test
    public void testCanary() {
        assertTrue(container.isRunning());
        System.out.println("TRACER container name: " + container.getContainerName());
    }
}
