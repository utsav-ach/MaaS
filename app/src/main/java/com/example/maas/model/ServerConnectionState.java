package com.example.maas.model;

/**
 * Represents the communication and runtime states of the MaaS Linux server environment.
 * Used for lifecycle and status reporting across the management layer.
 */
public enum ServerConnectionState {
    CONNECTED("Connected"),
    DISCONNECTED("Disconnected"),
    CONNECTING("Connecting"),
    ERROR("Error"),
    NOT_CONFIGURED("Not Configured");

    private final String label;

    ServerConnectionState(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
