package com.example.maas.model;

import java.util.Objects;

/**
 * Architectural data model representing the runtime status of the MaaS edge server.
 */
public class ServerStatus {

    private final ServerConnectionState status;
    private final String uptime;
    private final String address;
    private final String description;

    public ServerStatus(ServerConnectionState status, String uptime, String address) {
        this(status, uptime, address, "Edge server environment uninitialized.");
    }

    public ServerStatus(ServerConnectionState status, String uptime, String address, String description) {
        this.status = status != null ? status : ServerConnectionState.NOT_CONFIGURED;
        this.uptime = uptime != null ? uptime : "0s";
        this.address = address != null ? address : "Not Connected";
        this.description = description != null ? description : "";
    }

    /**
     * Default state for Day 2: server is not connected / unconfigured.
     */
    public static ServerStatus createDefault() {
        return new ServerStatus(
                ServerConnectionState.NOT_CONFIGURED,
                "0s",
                "Not Connected",
                "The Linux server environment (Termux / Ubuntu layer) is currently not configured or running."
        );
    }

    public ServerConnectionState getStatus() {
        return status;
    }

    public String getUptime() {
        return uptime;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public boolean isOnline() {
        return status == ServerConnectionState.CONNECTED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServerStatus that = (ServerStatus) o;
        return status == that.status &&
                Objects.equals(uptime, that.uptime) &&
                Objects.equals(address, that.address) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, uptime, address, description);
    }

    @Override
    public String toString() {
        return "ServerStatus{" +
                "status=" + status +
                ", uptime='" + uptime + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
