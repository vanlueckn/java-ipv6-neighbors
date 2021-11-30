package dev.vanlueck.ipv6.ndp.common.parser;

import dev.vanlueck.ipv6.ndp.api.Neighbor;
import dev.vanlueck.ipv6.ndp.api.exception.ParserException;
import dev.vanlueck.ipv6.ndp.common.NeighborImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

public class Linux implements Parser {
    @Override
    public Set<Neighbor> parse(String text) throws IOException, ParserException {
        if (text == null) throw new ParserException(ERROR_MESSAGE);
        Set<Neighbor> set = new HashSet<>();

        BufferedReader bufReader = new BufferedReader(new StringReader(text));
        String line;
        while ((line = bufReader.readLine()) != null) {
            String trimmedLine = line.trim();
            if (trimmedLine.length() == 0) continue;

            String[] splittedLine = trimmedLine.split(" ");
            if (splittedLine.length != 7)
                throw new ParserException(ERROR_MESSAGE);

            set.add(NeighborImpl.fromLinuxStringArray(splittedLine));
        }

        return set;
    }
}
