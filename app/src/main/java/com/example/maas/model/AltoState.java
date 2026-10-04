package com.example.maas.model;

/**
 * Encapsulates the state of the Adaptive Load & Traffic Orchestrator (ALTO).
 * Day 1 initializes this with inactive/standby state.
 */
public class AltoState {
    private final String status;
    private final String activePolicy;
    private final String description;
    private final boolean isOrchestrating;

    public AltoState(String status, String activePolicy, String description, boolean isOrchestrating) {
        this.status = status;
        this.activePolicy = activePolicy;
        this.description = description;
        this.isOrchestrating = isOrchestrating;
    }

    /**
     * Default state for Day 1: ALTO standby awaiting server runtime.
     */
    public static AltoState createInitialDefault() {
        return new AltoState(
                "Not Active",
                "Active Policy: Standby (Awaiting Server)",
                "ALTO handles edge load throttling, battery/thermal-aware request distribution, and traffic offloading when the edge host reaches threshold limits.",
                false
        );
    }

    public String getStatus() { return status; }
    public String getActivePolicy() { return activePolicy; }
    public String getDescription() { return description; }
    public boolean isOrchestrating() { return isOrchestrating; }
}
