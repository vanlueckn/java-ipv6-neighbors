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

class TestBsd {
    @Test
    @DisplayName("Test BSD parser")
    @Timeout(value = 10)
    void testBsdParser() {
        String text = """
                Neighbor                             Linklayer Address  Netif Expire    S Flags
                fe80::250:56ff:fe00:6d36%hn1         00:50:56:00:6d:36    hn1 permanent R""";
        Parser parser = new Bsd();
        try {
            Set<Neighbor> neighbors = parser.parse(text);
            Assertions.assertNotNull(neighbors);
            Assertions.assertEquals(1, neighbors.size());
            Neighbor neighbor = neighbors.iterator().next();
            Assertions.assertNotNull(neighbor);
            Assertions.assertEquals("fe80::250:56ff:fe00:6d36%hn1", neighbor.getAddress());
            Assertions.assertEquals("hn1", neighbor.getDevice());
            Assertions.assertEquals("00:50:56:00:6d:36", neighbor.getLladdr());
            Assertions.assertEquals(RouterState.REACHABLE, neighbor.getState());

        } catch (ParserException | IOException e) {
            Assertions.fail(e);
        }

    }

    @Test
    @DisplayName("Test MAC parser")
    @Timeout(value = 10)
    void testMacParser() {
        String text = """
                Neighbor                        Linklayer Address  Netif Expire    St Flgs Prbs
                fe80::1c80:60ff:fe2e:5791%anpi1 1e:80:60:2e:57:91  anpi1 permanent R""";
        Parser parser = new Bsd();
        try {
            Set<Neighbor> neighbors = parser.parse(text);
            Assertions.assertNotNull(neighbors);
            Assertions.assertEquals(1, neighbors.size());
            Neighbor neighbor = neighbors.iterator().next();
            Assertions.assertNotNull(neighbor);
            Assertions.assertEquals("fe80::1c80:60ff:fe2e:5791%anpi1", neighbor.getAddress());
            Assertions.assertEquals("anpi1", neighbor.getDevice());
            Assertions.assertEquals("1e:80:60:2e:57:91", neighbor.getLladdr());
            Assertions.assertEquals(RouterState.REACHABLE, neighbor.getState());

        } catch (ParserException | IOException e) {
            Assertions.fail(e);
        }

    }
}
