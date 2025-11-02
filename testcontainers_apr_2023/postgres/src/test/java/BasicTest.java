import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
public class BasicTest {
    @Container
    private final GenericContainer<?> container = new GenericContainer<>(DockerImageName.parse("postgres")).withEnv("POSTGRES_PASSWORD", "test");

    @Test
    public void testCanary() {
        assertTrue(container.isRunning());
        System.out.println("TRACER container name: " + container.getContainerName());
    }
}
