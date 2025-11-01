import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import static org.junit.Assert.*;

public class BasicTest {

    @Test
    public void testCanary() {
        final var container = new GenericContainer<>(DockerImageName.parse("postgres"));
        container.start();
        assertTrue(container.isRunning());
        container.stop();
    }
}
