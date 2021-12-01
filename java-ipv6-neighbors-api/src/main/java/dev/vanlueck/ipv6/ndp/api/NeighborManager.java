package dev.vanlueck.ipv6.ndp.api;


import dev.vanlueck.ipv6.ndp.api.exception.ParserException;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public interface NeighborManager {
    Set<Neighbor> getNeighbors() throws ParserException, IOException;

    CompletableFuture<Set<Neighbor>> getNeighborsAsync();

    CompletableFuture<List<Neighbor>> filterDevice(String device);
}
