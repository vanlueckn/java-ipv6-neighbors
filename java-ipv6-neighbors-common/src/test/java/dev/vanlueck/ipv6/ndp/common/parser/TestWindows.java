package dev.vanlueck.ipv6.ndp.common.parser;

import dev.vanlueck.ipv6.ndp.common.Neighbor;
import dev.vanlueck.ipv6.ndp.common.exception.ParserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Set;

class TestWindows {
    @Test
    @DisplayName("Test Windows Parser")
    void testParse() {
        String testText = """
                    State                      : Permanent
                    Store                      : ActiveStore
                    AddressFamily              : IPv6
                    ifIndex                    : 1
                    Caption                    :
                    Description                :
                    ElementName                :
                    InstanceID                 :
                    CommunicationStatus        :
                    DetailedStatus             :
                    HealthState                :
                    InstallDate                :
                    Name                       : pp:<DD<55;55;
                    OperatingStatus            :
                    OperationalStatus          :
                    PrimaryStatus              :
                    Status                     :
                    StatusDescriptions         :
                    AvailableRequestedStates   :
                    EnabledDefault             : 2
                    EnabledState               : 5
                    OtherEnabledState          :
                    RequestedState             : 12
                    TimeOfLastStateChange      :
                    TransitioningToState       : 12
                    CreationClassName          :
                    SystemCreationClassName    :
                    SystemName                 :
                    AccessContext              : 0
                    AccessInfo                 :
                    InfoFormat                 :
                    OtherAccessContext         :
                    OtherInfoFormatDescription :
                    InterfaceAlias             : Loopback Pseudo-Interface 1
                    InterfaceIndex             : 1
                    IPAddress                  : ff02::2
                    LinkLayerAddress           :
                    PSComputerName             :
                    CimClass                   : ROOT/StandardCimv2:MSFT_NetNeighbor
                    CimInstanceProperties      : {Caption, Description, ElementName, InstanceID...}
                    CimSystemProperties        : Microsoft.Management.Infrastructure.CimSystemProperties
                    
                    State                      : Permanent
                    Store                      : ActiveStore
                    AddressFamily              : IPv4
                    ifIndex                    : 22
                    Caption                    :
                    Description                :
                    ElementName                :
                    InstanceID                 :
                    CommunicationStatus        :
                    DetailedStatus             :
                    HealthState                :
                    InstallDate                :
                    Name                       : <=C8<??8<??8<?:55<<55;
                    OperatingStatus            :
                    OperationalStatus          :
                    PrimaryStatus              :
                    Status                     :
                    StatusDescriptions         :
                    AvailableRequestedStates   :
                    EnabledDefault             : 2
                    EnabledState               : 5
                    OtherEnabledState          :
                    RequestedState             : 12
                    TimeOfLastStateChange      :
                    TransitioningToState       : 12
                    CreationClassName          :
                    SystemCreationClassName    :
                    SystemName                 :
                    AccessContext              : 0
                    AccessInfo                 :
                    InfoFormat                 :
                    OtherAccessContext         :
                    OtherInfoFormatDescription :
                    InterfaceAlias             : vEthernet (WSL)
                    InterfaceIndex             : 22
                    IPAddress                  : 239.255.255.250
                    LinkLayerAddress           : 01-00-5E-7F-FF-FA
                    PSComputerName             :
                    CimClass                   : ROOT/StandardCimv2:MSFT_NetNeighbor
                    CimInstanceProperties      : {Caption, Description, ElementName, InstanceID...}
                    CimSystemProperties        : Microsoft.Management.Infrastructure.CimSystemProperties
                    
                    State                      : Permanent
                    Store                      : ActiveStore
                    AddressFamily              : IPv4
                    ifIndex                    : 22
                    Caption                    :
                    Description                :
                    ElementName                :
                    InstanceID                 :
                    CommunicationStatus        :
                    DetailedStatus             :
                    HealthState                :
                    InstallDate                :
                    Name                       : <<>8:8<8@:55<<55;
                    OperatingStatus            :
                    OperationalStatus          :
                    PrimaryStatus              :
                    Status                     :
                    StatusDescriptions         :
                    AvailableRequestedStates   :
                    EnabledDefault             : 2
                    EnabledState               : 5
                    OtherEnabledState          :
                    RequestedState             : 12
                    TimeOfLastStateChange      :
                    TransitioningToState       : 12
                    CreationClassName          :
                    SystemCreationClassName    :
                    SystemName                 :
                    AccessContext              : 0
                    AccessInfo                 :
                    InfoFormat                 :
                    OtherAccessContext         :
                    OtherInfoFormatDescription :
                    InterfaceAlias             : vEthernet (WSL)
                    InterfaceIndex             : 22
                    IPAddress                  : 224.0.2.60
                    LinkLayerAddress           : 01-00-5E-00-02-3C
                    PSComputerName             :
                    CimClass                   : ROOT/StandardCimv2:MSFT_NetNeighbor
                    CimInstanceProperties      : {Caption, Description, ElementName, InstanceID...}
                    CimSystemProperties        : Microsoft.Management.Infrastructure.CimSystemProperties
                    
                    State                      : Permanent
                    Store                      : ActiveStore
                    AddressFamily              : IPv4
                    ifIndex                    : 22
                    Caption                    :
                    Description                :
                    ElementName                :
                    InstanceID                 :
                    CommunicationStatus        :
                    DetailedStatus             :
                    HealthState                :
                    InstallDate                :
                    Name                       : <<>8:8:8<?;55<<55;
                    OperatingStatus            :
                    OperationalStatus          :
                    PrimaryStatus              :
                    Status                     :
                    StatusDescriptions         :
                    AvailableRequestedStates   :
                    EnabledDefault             : 2
                    EnabledState               : 5
                    OtherEnabledState          :
                    RequestedState             : 12
                    TimeOfLastStateChange      :
                    TransitioningToState       : 12
                    CreationClassName          :
                    SystemCreationClassName    :
                    SystemName                 :
                    AccessContext              : 0
                    AccessInfo                 :
                    InfoFormat                 :
                    OtherAccessContext         :
                    OtherInfoFormatDescription :
                    InterfaceAlias             : vEthernet (WSL)
                    InterfaceIndex             : 22
                    IPAddress                  : 224.0.0.251
                    LinkLayerAddress           : 01-00-5E-00-00-FB
                    PSComputerName             :
                    CimClass                   : ROOT/StandardCimv2:MSFT_NetNeighbor
                    CimInstanceProperties      : {Caption, Description, ElementName, InstanceID...}
                    CimSystemProperties        : Microsoft.Management.Infrastructure.CimSystemProperties
                    
                    State                      : Permanent
                    Store                      : ActiveStore
                    AddressFamily              : IPv6
                    ifIndex                    : 1
                    Caption                    :
                    Description                :
                    ElementName                :
                    InstanceID                 :
                    CommunicationStatus        :
                    DetailedStatus             :
                    HealthState                :
                    InstallDate                :
                    Name                       : pp:<DD<55;55;
                    OperatingStatus            :
                    OperationalStatus          :
                    PrimaryStatus              :
                    Status                     :
                    StatusDescriptions         :
                    AvailableRequestedStates   :
                    EnabledDefault             : 2
                    EnabledState               : 5
                    OtherEnabledState          :
                    RequestedState             : 12
                    TimeOfLastStateChange      :
                    TransitioningToState       : 12
                    CreationClassName          :
                    SystemCreationClassName    :
                    SystemName                 :
                    AccessContext              : 0
                    AccessInfo                 :
                    InfoFormat                 :
                    OtherAccessContext         :
                    OtherInfoFormatDescription :
                    InterfaceAlias             : Loopback Pseudo-Interface 1
                    InterfaceIndex             : 1
                    IPAddress                  : ff02::2
                    LinkLayerAddress           :
                    PSComputerName             :
                    CimClass                   : ROOT/StandardCimv2:MSFT_NetNeighbor
                    CimInstanceProperties      : {Caption, Description, ElementName, InstanceID...}
                    CimSystemProperties        : Microsoft.Management.Infrastructure.CimSystemProperties
                """;

        try {
            Parser parser = new Windows();
            Set<Neighbor> neighbors = parser.parse(testText);
            Assertions.assertNotNull(neighbors);
            Assertions.assertEquals(2, neighbors.size());
            Neighbor neighbor = neighbors.iterator().next();
            Assertions.assertNotNull(neighbor);

        } catch (IOException | ParserException e) {
            Assertions.fail(e);
        }
    }
}
