package dev.vanlueck.ipv6.ndp.common;

import dev.vanlueck.ipv6.ndp.common.exception.ParserException;
import dev.vanlueck.ipv6.ndp.common.exception.UnsupportedOSException;
import dev.vanlueck.ipv6.ndp.common.parser.Linux;
import dev.vanlueck.ipv6.ndp.common.parser.Parser;
import dev.vanlueck.ipv6.ndp.common.parser.Windows;
import dev.vanlueck.ipv6.ndp.common.util.OSValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class NeighborManagerImpl {

    private final Parser parser;
    private final String[] commands;

    public NeighborManagerImpl() throws UnsupportedOSException {
        if (OSValidator.isWindows()) {
            this.parser = new Windows();
            this.commands = new String[]{"powershell.exe", "-noprofile", "-command", "\"Get-NetNeighbor | Format-List -Property *\""};
        } else if (OSValidator.isUnix()) {
            this.parser = new Linux();
            this.commands = new String[]{"ip", "-6", "neigh"};
        } else {
            throw new UnsupportedOSException("OS not supported");
        }
    }

    public Set<Neighbor> getNeighbors() throws ParserException, IOException {
        String text = this.getText();
        return this.parser.parse(text);
    }

    public CompletableFuture<Set<Neighbor>> getNeighborsAsync() {
        return this.getTextAsync().thenCompose(s -> {
            try {
                return CompletableFuture.completedFuture(this.parser.parse(s));
            } catch (IOException | ParserException e) {
                return CompletableFuture.failedFuture(e);
            }
        });
    }

    private CompletableFuture<String> getTextAsync() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return this.getText();
            } catch (IOException | ParserException e) {
                throw new CompletionException(e);
            }
        });
    }

    private String getText() throws IOException, ParserException {
        Runtime rt = Runtime.getRuntime();
        Process proc = rt.exec(this.commands);

        BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(proc.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(proc.getErrorStream()));

        StringBuilder text = new StringBuilder();
        String s;
        while ((s = stdInput.readLine()) != null) {
            text.append("\\n").append(s);
        }

        if (stdError.readLine() != null) {
            throw new ParserException("Cannot parse console string");
        }

        return text.toString();
    }
}
