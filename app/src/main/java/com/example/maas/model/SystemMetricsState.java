package com.example.maas.model;

/**
 * Encapsulates hardware resource metrics for the edge host device.
 * Day 1 initializes this with static placeholders to represent unmonitored baseline.
 */
public class SystemMetricsState {
    private final String cpuValue;
    private final String cpuState;
    private final String ramValue;
    private final String ramState;
    private final String tempValue;
    private final String tempState;
    private final String batteryValue;
    private final String batteryState;
    private final String disclaimer;

    public SystemMetricsState(String cpuValue, String cpuState,
                              String ramValue, String ramState,
                              String tempValue, String tempState,
                              String batteryValue, String batteryState,
                              String disclaimer) {
        this.cpuValue = cpuValue;
        this.cpuState = cpuState;
        this.ramValue = ramValue;
        this.ramState = ramState;
        this.tempValue = tempValue;
        this.tempState = tempState;
        this.batteryValue = batteryValue;
        this.batteryState = batteryState;
        this.disclaimer = disclaimer;
    }

    /**
     * Default state for Day 1: hardware metrics unmonitored / offline.
     */
    public static SystemMetricsState createPlaceholderDefault() {
        return new SystemMetricsState(
                "— %",
                "Offline",
                "— / — GB",
                "Standby",
                "— °C",
                "Unmonitored",
                "— %",
                "Standby",
                "[Day 1 Initial State] Real hardware telemetry will be connected in the monitoring chunk."
        );
    }

    public String getCpuValue() { return cpuValue; }
    public String getCpuState() { return cpuState; }
    public String getRamValue() { return ramValue; }
    public String getRamState() { return ramState; }
    public String getTempValue() { return tempValue; }
    public String getTempState() { return tempState; }
    public String getBatteryValue() { return batteryValue; }
    public String getBatteryState() { return batteryState; }
    public String getDisclaimer() { return disclaimer; }
}
