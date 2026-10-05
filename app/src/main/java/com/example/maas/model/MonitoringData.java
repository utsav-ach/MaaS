package com.example.maas.model;

import java.util.Objects;

/**
 * Architectural data model representing hardware resource monitoring telemetry for MaaS host.
 */
public class MonitoringData {

    private final double cpuUsage;
    private final double memoryUsage;
    private final double temperature;
    private final int batteryLevel;
    private final boolean isMonitored;

    public MonitoringData(double cpuUsage, double memoryUsage, double temperature, int batteryLevel, boolean isMonitored) {
        this.cpuUsage = cpuUsage;
        this.memoryUsage = memoryUsage;
        this.temperature = temperature;
        this.batteryLevel = batteryLevel;
        this.isMonitored = isMonitored;
    }

    /**
     * Default unmonitored state for Day 2: real telemetry not connected yet.
     */
    public static MonitoringData createUnmonitoredDefault() {
        return new MonitoringData(-1.0, -1.0, -1.0, -1, false);
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public double getMemoryUsage() {
        return memoryUsage;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public boolean isMonitored() {
        return isMonitored;
    }

    public String getCpuUsageFormatted() {
        return isMonitored ? String.format("%.1f%%", cpuUsage) : "— %";
    }

    public String getMemoryUsageFormatted() {
        return isMonitored ? String.format("%.1f%%", memoryUsage) : "— / — GB";
    }

    public String getTemperatureFormatted() {
        return isMonitored ? String.format("%.1f°C", temperature) : "— °C";
    }

    public String getBatteryLevelFormatted() {
        return isMonitored ? String.format("%d%%", batteryLevel) : "— %";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonitoringData that = (MonitoringData) o;
        return Double.compare(that.cpuUsage, cpuUsage) == 0 &&
                Double.compare(that.memoryUsage, memoryUsage) == 0 &&
                Double.compare(that.temperature, temperature) == 0 &&
                batteryLevel == that.batteryLevel &&
                isMonitored == that.isMonitored;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpuUsage, memoryUsage, temperature, batteryLevel, isMonitored);
    }

    @Override
    public String toString() {
        return "MonitoringData{" +
                "cpuUsage=" + cpuUsage +
                ", memoryUsage=" + memoryUsage +
                ", temperature=" + temperature +
                ", batteryLevel=" + batteryLevel +
                ", isMonitored=" + isMonitored +
                '}';
    }
}
