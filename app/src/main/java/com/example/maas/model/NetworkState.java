package com.example.maas.model;

/**
 * Encapsulates network interfaces and Cloudflare Tunnel routing states.
 * Day 1 initializes this with disconnected/unconfigured states.
 */
public class NetworkState {
    private final String localIpAddress;
    private final String publicTunnelUrl;
    private final String connectionStatus;
    private final boolean isConnected;

    public NetworkState(String localIpAddress, String publicTunnelUrl,
                        String connectionStatus, boolean isConnected) {
        this.localIpAddress = localIpAddress;
        this.publicTunnelUrl = publicTunnelUrl;
        this.connectionStatus = connectionStatus;
        this.isConnected = isConnected;
    }

    /**
     * Default state for Day 1: disconnected and tunnel unconfigured.
     */
    public static NetworkState createInitialDefault() {
        return new NetworkState(
                "Unavailable",
                "Not Configured",
                "Disconnected",
                false
        );
    }

    public String getLocalIpAddress() { return localIpAddress; }
    public String getPublicTunnelUrl() { return publicTunnelUrl; }
    public String getConnectionStatus() { return connectionStatus; }
    public boolean isConnected() { return isConnected; }
}
