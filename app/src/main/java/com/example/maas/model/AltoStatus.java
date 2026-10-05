package com.example.maas.model;

import java.util.Objects;

/**
 * Architectural data model representing the Adaptive Load Throttling and Optimization (ALTO) engine status.
 */
public class AltoStatus {

    private final boolean enabled;
    private final String state;
    private final double loadScore;
    private final String policy;

    public AltoStatus(boolean enabled, String state, double loadScore) {
        this(enabled, state, loadScore, "Standby (Awaiting Server)");
    }

    public AltoStatus(boolean enabled, String state, double loadScore, String policy) {
        this.enabled = enabled;
        this.state = state != null ? state : "Not Active";
        this.loadScore = loadScore;
        this.policy = policy != null ? policy : "Standby";
    }

    /**
     * Default state for Day 2: ALTO is not active / uninitialized.
     */
    public static AltoStatus createDefault() {
        return new AltoStatus(false, "Not Active", 0.0, "Standby (Awaiting Server)");
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getState() {
        return state;
    }

    public double getLoadScore() {
        return loadScore;
    }

    public String getPolicy() {
        return policy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AltoStatus that = (AltoStatus) o;
        return enabled == that.enabled &&
                Double.compare(that.loadScore, loadScore) == 0 &&
                Objects.equals(state, that.state) &&
                Objects.equals(policy, that.policy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enabled, state, loadScore, policy);
    }

    @Override
    public String toString() {
        return "AltoStatus{" +
                "enabled=" + enabled +
                ", state='" + state + '\'' +
                ", loadScore=" + loadScore +
                ", policy='" + policy + '\'' +
                '}';
    }
}
