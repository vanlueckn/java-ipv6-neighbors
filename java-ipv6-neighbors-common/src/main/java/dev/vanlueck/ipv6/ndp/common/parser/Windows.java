package dev.vanlueck.ipv6.ndp.common.parser;

import dev.vanlueck.ipv6.ndp.api.Neighbor;
import dev.vanlueck.ipv6.ndp.api.exception.ParserException;
import dev.vanlueck.ipv6.ndp.common.NeighborImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class Windows implements Parser {

    @Override
    public Set<Neighbor> parse(String text) throws IOException, ParserException {
        if (text == null) throw new ParserException(ERROR_MESSAGE + "A");
        Set<Neighbor> set = new HashSet<>();
        Set<List<String>> blocks = new HashSet<>();

        BufferedReader bufReaderBlock = new BufferedReader(new StringReader(text));
        String blockLine;
        List<String> currentBlock = new ArrayList<>();
        boolean first = true;

        while ((blockLine = bufReaderBlock.readLine()) != null) {
            if (blockLine.trim().startsWith("State") && !first) {
                blocks.add(List.copyOf(currentBlock));

                currentBlock.clear();
            }

            currentBlock.add(blockLine.trim());
            first = false;
        }

        blocks.forEach(neighborBlock -> {
            Map<String, String> properties = new HashMap<>();
            neighborBlock.forEach(line -> {

                String[] splittedLine = line.split(":", 2);
                if (splittedLine.length != 2)
                    return;

                String firstPart = splittedLine[0].trim();
                String secondPart = splittedLine[1].trim();

                properties.put(firstPart, secondPart);
            });

            String addressFamily = properties.get("AddressFamily");
            if (!"IPv6".equals(addressFamily)) return;

            set.add(NeighborImpl.fromWindowsHashMap(properties));
        });

        return set;
    }
}
