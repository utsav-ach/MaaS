package com.example.maas.model;

/**
 * Encapsulates the runtime status state of the MaaS server environment.
 * Day 1 initializes this with static offline/standby state.
 */
public class ServerStatusState {
    private final boolean isOnline;
    private final String badgeText;
    private final String statusHeadline;
    private final String statusDescription;
    private final String runtimeTarget;

    public ServerStatusState(boolean isOnline, String badgeText, String statusHeadline,
                             String statusDescription, String runtimeTarget) {
        this.isOnline = isOnline;
        this.badgeText = badgeText;
        this.statusHeadline = statusHeadline;
        this.statusDescription = statusDescription;
        this.runtimeTarget = runtimeTarget;
    }

    /**
     * Default state for Day 1: server environment offline and uninitialized.
     */
    public static ServerStatusState createOfflineDefault() {
        return new ServerStatusState(
                false,
                "OFFLINE",
                "Currently Offline / Not Connected",
                "The Linux Server Environment (Termux / Ubuntu layer) is currently not running. Server process controls will be integrated in upcoming chunks.",
                "Target Runtime: Ubuntu 22.04 LTS via PRoot / Termux"
        );
    }

    public boolean isOnline() {
        return isOnline;
    }

    public String getBadgeText() {
        return badgeText;
    }

    public String getStatusHeadline() {
        return statusHeadline;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

    public String getRuntimeTarget() {
        return runtimeTarget;
    }
}
