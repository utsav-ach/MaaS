package com.example.maas.model;

/**
 * Encapsulates the state of runtime daemons and hosted web applications.
 * Day 1 initializes this with static zero/stopped states.
 */
public class ServicesState {
    private final int activeServicesCount;
    private final int deployedAppsCount;
    private final boolean nginxRunning;
    private final boolean dbRunning;
    private final boolean tunnelRunning;

    public ServicesState(int activeServicesCount, int deployedAppsCount,
                         boolean nginxRunning, boolean dbRunning, boolean tunnelRunning) {
        this.activeServicesCount = activeServicesCount;
        this.deployedAppsCount = deployedAppsCount;
        this.nginxRunning = nginxRunning;
        this.dbRunning = dbRunning;
        this.tunnelRunning = tunnelRunning;
    }

    /**
     * Default state for Day 1: 0 running daemons, 0 deployed apps.
     */
    public static ServicesState createInitialDefault() {
        return new ServicesState(0, 0, false, false, false);
    }

    public int getActiveServicesCount() { return activeServicesCount; }
    public int getDeployedAppsCount() { return deployedAppsCount; }
    public boolean isNginxRunning() { return nginxRunning; }
    public boolean isDbRunning() { return dbRunning; }
    public boolean isTunnelRunning() { return tunnelRunning; }
}
