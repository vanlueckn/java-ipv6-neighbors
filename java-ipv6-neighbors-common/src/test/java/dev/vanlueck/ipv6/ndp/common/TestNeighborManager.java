package dev.vanlueck.ipv6.ndp.common;

import dev.vanlueck.ipv6.ndp.api.Neighbor;
import dev.vanlueck.ipv6.ndp.api.NeighborManager;
import dev.vanlueck.ipv6.ndp.api.exception.ParserException;
import dev.vanlueck.ipv6.ndp.api.exception.UnsupportedOSException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.IOException;
import java.util.Set;

class TestNeighborManager {

    @Test
    @DisplayName("Test Neighbor Manager implementation sync")
    @Timeout(value = 10)
    void testNeighborManagerSync() {
        try {
            NeighborManager neighborManager = new NeighborManagerImpl();
            Set<Neighbor> neighbors = neighborManager.getNeighbors();
            Assertions.assertNotNull(neighbors);
        } catch (UnsupportedOSException | ParserException | IOException e) {
            Assertions.fail(e);
        }
    }

    @Test
    @DisplayName("Test Neighbor Manager implementation async")
    @Timeout(value = 10)
    void testNeighborManagerAsync() {
        try {
            NeighborManager neighborManager = new NeighborManagerImpl();
            neighborManager.getNeighborsAsync().whenComplete((neighbors, throwable) -> {
                if (throwable != null) Assertions.fail(throwable);
                Assertions.assertNotNull(neighbors);
            });
        } catch (UnsupportedOSException e) {
            Assertions.fail(e);
        }
    }
}
