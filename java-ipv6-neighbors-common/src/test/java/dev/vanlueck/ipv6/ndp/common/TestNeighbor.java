package dev.vanlueck.ipv6.ndp.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class TestNeighbor {

    @Test
    @DisplayName("Test fromWindowsHashmap")
    void testFromWindowsHashmap() {
        Map<String, String> testStrings = new HashMap<>();

        testStrings.put("State", "Permanent");
        testStrings.put("AddressFamily", "IPv6");
        testStrings.put("InterfaceAlias", "vEthernet (WSL)");
        testStrings.put("IPAddress", "ff0e::c");
        testStrings.put("LinkLayerAddress", "33-33-00-00-00-0C");

        Neighbor neighbor = Neighbor.fromWindowsHashMap(testStrings);

        Assertions.assertNotNull(neighbor);
        Assertions.assertEquals("ff0e::c", neighbor.getAddress());
    }
}
