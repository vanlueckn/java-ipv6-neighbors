package dev.vanlueck.ipv6.ndp.common.parser;

import dev.vanlueck.ipv6.ndp.api.Neighbor;
import dev.vanlueck.ipv6.ndp.api.RouterState;
import dev.vanlueck.ipv6.ndp.api.exception.ParserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

class TestLinux {
    @Test
    @DisplayName("Test Linux parser")
    @Timeout(value = 10)
    void testLinuxParser() {
        String text = "fe80::1 dev eth0 lladdr 0c:86:10:ed:34:d9 router STALE";
        Parser parser = new Linux();
        try {
            Set<Neighbor> neighbors = parser.parse(text);
            Assertions.assertNotNull(neighbors);
            Assertions.assertEquals(1, neighbors.size());
            Neighbor neighbor = neighbors.iterator().next();
            Assertions.assertNotNull(neighbor);
            Assertions.assertEquals("fe80::1", neighbor.getAddress());
            Assertions.assertEquals("eth0", neighbor.getDevice());
            Assertions.assertEquals("0c:86:10:ed:34:d9", neighbor.getLladdr());
            Assertions.assertEquals(RouterState.STALE, neighbor.getState());

        } catch (ParserException | IOException e) {
            Assertions.fail(e);
        }

    }
}
