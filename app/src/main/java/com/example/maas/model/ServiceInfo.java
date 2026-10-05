package com.example.maas.model;

import java.util.Objects;

/**
 * Architectural data model representing a background system daemon or runtime service on MaaS.
 */
public class ServiceInfo {

    private final String name;
    private final String status;
    private final int port;

    public ServiceInfo(String name, String status, int port) {
        this.name = name != null ? name : "Unknown Service";
        this.status = status != null ? status : "Stopped";
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public int getPort() {
        return port;
    }

    public boolean isRunning() {
        return "Running".equalsIgnoreCase(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceInfo that = (ServiceInfo) o;
        return port == that.port &&
                Objects.equals(name, that.name) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, status, port);
    }

    @Override
    public String toString() {
        return "ServiceInfo{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", port=" + port +
                '}';
    }
}
