package com.example.maas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import com.example.maas.model.AltoState;
import com.example.maas.model.NetworkState;
import com.example.maas.model.ServerStatusState;
import com.example.maas.model.ServicesState;
import com.example.maas.model.SystemMetricsState;
import org.junit.Test;

/**
 * Unit tests verifying Day 1 model baseline and placeholder contract integrity.
 */
public class ModelBaselineTest {

    @Test
    public void testServerStatusOfflineDefault() {
        ServerStatusState state = ServerStatusState.createOfflineDefault();
        assertNotNull(state);
        assertFalse(state.isOnline());
        assertEquals("OFFLINE", state.getBadgeText());
        assertEquals("Currently Offline / Not Connected", state.getStatusHeadline());
    }

    @Test
    public void testSystemMetricsPlaceholderDefault() {
        SystemMetricsState state = SystemMetricsState.createPlaceholderDefault();
        assertNotNull(state);
        assertEquals("— %", state.getCpuValue());
        assertEquals("Offline", state.getCpuState());
        assertEquals("— / — GB", state.getRamValue());
        assertEquals("Standby", state.getRamState());
        assertEquals("— °C", state.getTempValue());
        assertEquals("Unmonitored", state.getTempState());
        assertEquals("— %", state.getBatteryValue());
        assertEquals("Standby", state.getBatteryState());
        assertNotNull(state.getDisclaimer());
    }

    @Test
    public void testServicesInitialDefault() {
        ServicesState state = ServicesState.createInitialDefault();
        assertNotNull(state);
        assertEquals(0, state.getActiveServicesCount());
        assertEquals(0, state.getDeployedAppsCount());
        assertFalse(state.isNginxRunning());
        assertFalse(state.isDbRunning());
        assertFalse(state.isTunnelRunning());
    }

    @Test
    public void testNetworkInitialDefault() {
        NetworkState state = NetworkState.createInitialDefault();
        assertNotNull(state);
        assertFalse(state.isConnected());
        assertEquals("Unavailable", state.getLocalIpAddress());
        assertEquals("Not Configured", state.getPublicTunnelUrl());
        assertEquals("Disconnected", state.getConnectionStatus());
    }

    @Test
    public void testAltoInitialDefault() {
        AltoState state = AltoState.createInitialDefault();
        assertNotNull(state);
        assertFalse(state.isOrchestrating());
        assertEquals("Not Active", state.getStatus());
        assertNotNull(state.getActivePolicy());
    }
}
