package dev.vanlueck.ipv6.ndp.common;

import java.util.Map;

public class Neighbor {

    private String address;
    private String device;
    private String lladdr;
    private RouterState state;

    public static Neighbor fromWindowsHashMap(Map<String, String> hashMap) {
        Neighbor neighbor = new Neighbor();

        neighbor.setAddress(hashMap.get("IPAddress"));
        neighbor.setDevice(hashMap.get("InterfaceAlias"));
        neighbor.setLladdr(hashMap.get("LinkLayerAddress"));
        neighbor.setState(RouterState.fromString(hashMap.get("State")));

        return neighbor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getLladdr() {
        return lladdr;
    }

    public void setLladdr(String lladdr) {
        this.lladdr = lladdr;
    }

    public RouterState getState() {
        return state;
    }

    public void setState(RouterState state) {
        this.state = state;
    }
}
