package dev.vanlueck.ipv6.ndp.common.parser;

import dev.vanlueck.ipv6.ndp.api.Neighbor;
import dev.vanlueck.ipv6.ndp.api.exception.ParserException;
import dev.vanlueck.ipv6.ndp.common.NeighborImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Windows implements Parser {

    @Override
    public Set<Neighbor> parse(String text) throws IOException, ParserException {
        if (text == null) throw new ParserException(ERROR_MESSAGE);
        Set<Neighbor> set = new HashSet<>();

        String[] neighborBlocks = text.split("\\n\\n");
        for (String neighborBlock : neighborBlocks) {
            BufferedReader bufReader = new BufferedReader(new StringReader(neighborBlock));
            String line;
            Map<String, String> properties = new HashMap<>();
            while ((line = bufReader.readLine()) != null) {
                String[] splittedLine = line.split(":", 2);
                if (splittedLine.length != 2)
                    throw new ParserException(ERROR_MESSAGE);

                String firstPart = splittedLine[0].trim();
                String secondPart = splittedLine[1].trim();

                properties.put(firstPart, secondPart);
            }

            String addressFamily = properties.get("AddressFamily");
            if (addressFamily == null) throw new ParserException(ERROR_MESSAGE);

            if (!"IPv6".equals(addressFamily)) continue;

            set.add(NeighborImpl.fromWindowsHashMap(properties));
        }
        return set;
    }
}
