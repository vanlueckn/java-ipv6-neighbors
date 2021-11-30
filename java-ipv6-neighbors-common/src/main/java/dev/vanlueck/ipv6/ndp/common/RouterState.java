package dev.vanlueck.ipv6.ndp.common;

public enum RouterState {
    INCOMPLETE,
    REACHABLE,
    STALE,
    DELAY,
    PROBE,
    UNDEFINED;

    public static RouterState fromString(String string) {
        RouterState routerState;
        switch (string) {
            case "INCOMPLETE":
                routerState = RouterState.INCOMPLETE;
                break;
            case "REACHABLE":
                routerState = RouterState.REACHABLE;
                break;
            case "STALE":
                routerState = RouterState.STALE;
                break;
            case "DELAY":
                routerState = RouterState.DELAY;
                break;
            case "PROBE":
                routerState = RouterState.PROBE;
                break;
            default:
                routerState = RouterState.UNDEFINED;
        }

        return routerState;
    }
}
