package dev.vanlueck.ipv6.ndp.api;

public interface Neighbor {

    String getAddress();

    void setAddress(String address);

    String getDevice();

    void setDevice(String device);

    String getLladdr();

    void setLladdr(String lladdr);

    RouterState getState();

    void setState(RouterState state);
}
