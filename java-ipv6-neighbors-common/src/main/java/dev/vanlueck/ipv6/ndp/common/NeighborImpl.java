package dev.vanlueck.ipv6.ndp.common;

import dev.vanlueck.ipv6.ndp.api.Neighbor;
import dev.vanlueck.ipv6.ndp.api.RouterState;

import java.util.Map;


public class NeighborImpl implements Neighbor {

    private String address;
    private String device;
    private String lladdr;
    private RouterState state;

    public static Neighbor fromWindowsHashMap(Map<String, String> hashMap) {
        Neighbor neighbor = new NeighborImpl();

        neighbor.setAddress(hashMap.get("IPAddress"));
        neighbor.setDevice(hashMap.get("InterfaceAlias"));
        neighbor.setLladdr(hashMap.get("LinkLayerAddress"));
        neighbor.setState(RouterState.fromString(hashMap.get("State")));

        return neighbor;
    }

    public static Neighbor fromLinuxStringArray(String[] stringArray) {
        Neighbor neighbor = new NeighborImpl();

        neighbor.setAddress(stringArray[0]);
        neighbor.setDevice(stringArray[2]);
        neighbor.setLladdr(stringArray[4]);
        neighbor.setState(RouterState.fromString(stringArray[6]));

        return neighbor;
    }

    public static Neighbor fromBsdStringArray(String[] stringArray) {
        Neighbor neighbor = new NeighborImpl();

        neighbor.setAddress(stringArray[0]);
        neighbor.setLladdr(stringArray[1]);
        neighbor.setDevice(stringArray[2]);
        neighbor.setState(RouterState.fromBsdString(stringArray[4]));

        return neighbor;
    }


    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getDevice() {
        return device;
    }

    @Override
    public void setDevice(String device) {
        this.device = device;
    }

    @Override
    public String getLladdr() {
        return lladdr;
    }

    @Override
    public void setLladdr(String lladdr) {
        this.lladdr = lladdr;
    }

    @Override
    public RouterState getState() {
        return state;
    }

    @Override
    public void setState(RouterState state) {
        this.state = state;
    }
}
