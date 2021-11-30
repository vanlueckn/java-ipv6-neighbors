package dev.vanlueck.ipv6.ndp.common.parser;

import dev.vanlueck.ipv6.ndp.common.Neighbor;
import dev.vanlueck.ipv6.ndp.common.exception.ParserException;

import java.io.IOException;
import java.util.Set;

public interface Parser {
    String ERROR_MESSAGE = "String could not be parsed";

    Set<Neighbor> parse(String text) throws IOException, ParserException;
}
