package dev.vanlueck.ipv6.ndp.common.parser;

import dev.vanlueck.ipv6.ndp.api.Neighbor;
import dev.vanlueck.ipv6.ndp.api.exception.ParserException;
import dev.vanlueck.ipv6.ndp.common.NeighborImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

public class Bsd implements Parser{
    @Override
    public Set<Neighbor> parse(String text) throws IOException, ParserException {
        if (text == null) throw new ParserException(ERROR_MESSAGE);
        Set<Neighbor> set = new HashSet<>();

        BufferedReader bufReader = new BufferedReader(new StringReader(text));
        String line;
        boolean firstLine = true;
        while ((line = bufReader.readLine()) != null) {
            if(firstLine){
                firstLine = false;
                continue;
            }

            String trimmedLine = line.trim();

            String[] splittedLine = trimmedLine.split("[ \\t]+");
            if (splittedLine.length != 5)
                throw new ParserException(ERROR_MESSAGE);

            set.add(NeighborImpl.fromBsdStringArray(splittedLine));
        }

        return set;
    }
}
