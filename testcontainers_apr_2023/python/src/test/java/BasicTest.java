import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
public class BasicTest {
    private static final String IMAGE_NAME = "simple-python-server";
    private static final int PORT = 8888;

    @Container
    private final GenericContainer<?> container = new GenericContainer<>(DockerImageName.parse(IMAGE_NAME))
                                                        .withExposedPorts(PORT);

    @Test
    public void testCanary() {
        assertTrue(container.isRunning());
        System.out.println("TRACER container name: " + container.getContainerName()
            + " on port " + container.getMappedPort(PORT));
    }
}
