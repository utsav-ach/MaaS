package com.example.maas.model;

import java.util.Objects;

/**
 * Architectural data model representing network configuration and Cloudflare Tunnel routing.
 */
public class NetworkInfo {

    private final String localAddress;
    private final String publicAddress;
    private final String tunnelStatus;

    public NetworkInfo(String localAddress, String publicAddress, String tunnelStatus) {
        this.localAddress = localAddress != null ? localAddress : "Unavailable";
        this.publicAddress = publicAddress != null ? publicAddress : "Not Configured";
        this.tunnelStatus = tunnelStatus != null ? tunnelStatus : "Not Configured";
    }

    /**
     * Default state for Day 2: unconfigured and disconnected.
     */
    public static NetworkInfo createDefault() {
        return new NetworkInfo("Unavailable", "Not Configured", "Not Configured");
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public String getPublicAddress() {
        return publicAddress;
    }

    public String getTunnelStatus() {
        return tunnelStatus;
    }

    public boolean isConnected() {
        return !"Unavailable".equalsIgnoreCase(localAddress) && !"Not Configured".equalsIgnoreCase(tunnelStatus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetworkInfo that = (NetworkInfo) o;
        return Objects.equals(localAddress, that.localAddress) &&
                Objects.equals(publicAddress, that.publicAddress) &&
                Objects.equals(tunnelStatus, that.tunnelStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localAddress, publicAddress, tunnelStatus);
    }

    @Override
    public String toString() {
        return "NetworkInfo{" +
                "localAddress='" + localAddress + '\'' +
                ", publicAddress='" + publicAddress + '\'' +
                ", tunnelStatus='" + tunnelStatus + '\'' +
                '}';
    }
}
