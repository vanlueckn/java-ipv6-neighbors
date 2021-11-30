package dev.vanlueck.ipv6.ndp.api;

public enum RouterState {
    INCOMPLETE,
    REACHABLE,
    STALE,
    DELAY,
    PROBE,
    UNDEFINED;

    public static RouterState fromString(String string) {
        return switch (string) {
            case "INCOMPLETE" -> RouterState.INCOMPLETE;
            case "REACHABLE" -> RouterState.REACHABLE;
            case "STALE" -> RouterState.STALE;
            case "DELAY" -> RouterState.DELAY;
            case "PROBE" -> RouterState.PROBE;
            default -> RouterState.UNDEFINED;
        };
    }
}
