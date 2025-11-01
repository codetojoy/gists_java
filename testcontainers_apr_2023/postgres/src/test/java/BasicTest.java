import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.Assert.*;

@Testcontainers
public class BasicTest {
    @Container
    private final GenericContainer<?> container = new GenericContainer<>(DockerImageName.parse("postgres"));

    @Test
    public void testCanary() {
        container.start();
        assertTrue(container.isRunning());
        container.stop();
    }
}
