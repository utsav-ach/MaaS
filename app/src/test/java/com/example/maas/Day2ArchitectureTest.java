package com.example.maas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.example.maas.model.AltoStatus;
import com.example.maas.model.ApplicationInfo;
import com.example.maas.model.MonitoringData;
import com.example.maas.model.NetworkInfo;
import com.example.maas.model.ServerConnectionState;
import com.example.maas.model.ServerStatus;
import com.example.maas.model.ServiceInfo;
import com.example.maas.service.ServerControlManager;
import com.example.maas.service.ServerControlService;
import java.util.List;
import org.junit.Test;

/**
 * Unit tests for Day 2 modular architecture, data models, and server control abstraction layer.
 */
public class Day2ArchitectureTest {

    @Test
    public void testServerConnectionStateValues() {
        ServerConnectionState[] states = ServerConnectionState.values();
        assertEquals(5, states.length);
        assertEquals(ServerConnectionState.CONNECTED, ServerConnectionState.valueOf("CONNECTED"));
        assertEquals(ServerConnectionState.DISCONNECTED, ServerConnectionState.valueOf("DISCONNECTED"));
        assertEquals(ServerConnectionState.CONNECTING, ServerConnectionState.valueOf("CONNECTING"));
        assertEquals(ServerConnectionState.ERROR, ServerConnectionState.valueOf("ERROR"));
        assertEquals(ServerConnectionState.NOT_CONFIGURED, ServerConnectionState.valueOf("NOT_CONFIGURED"));

        assertEquals("Not Configured", ServerConnectionState.NOT_CONFIGURED.getLabel());
        assertEquals("Connected", ServerConnectionState.CONNECTED.getLabel());
    }

    @Test
    public void testServerStatusModelDefault() {
        ServerStatus status = ServerStatus.createDefault();
        assertNotNull(status);
        assertEquals(ServerConnectionState.NOT_CONFIGURED, status.getStatus());
        assertEquals("0s", status.getUptime());
        assertEquals("Not Connected", status.getAddress());
        assertFalse(status.isOnline());
        assertNotNull(status.getDescription());
    }

    @Test
    public void testServerStatusCustomInitialization() {
        ServerStatus onlineStatus = new ServerStatus(
                ServerConnectionState.CONNECTED,
                "1h 45m",
                "192.168.1.100:8080",
                "Ubuntu 22.04 LTS Active"
        );
        assertEquals(ServerConnectionState.CONNECTED, onlineStatus.getStatus());
        assertEquals("1h 45m", onlineStatus.getUptime());
        assertEquals("192.168.1.100:8080", onlineStatus.getAddress());
        assertTrue(onlineStatus.isOnline());
    }

    @Test
    public void testApplicationInfoModel() {
        ApplicationInfo app = new ApplicationInfo("Static Website", "Running", 8080);
        assertEquals("Static Website", app.getName());
        assertEquals("Running", app.getStatus());
        assertEquals(8080, app.getPort());
        assertTrue(app.isRunning());

        ApplicationInfo stoppedApp = new ApplicationInfo("Node API", "Stopped", 3000);
        assertFalse(stoppedApp.isRunning());
    }

    @Test
    public void testServiceInfoModel() {
        ServiceInfo nginx = new ServiceInfo("Nginx", "Stopped", 8080);
        assertEquals("Nginx", nginx.getName());
        assertEquals("Stopped", nginx.getStatus());
        assertEquals(8080, nginx.getPort());
        assertFalse(nginx.isRunning());

        ServiceInfo runningService = new ServiceInfo("MariaDB", "Running", 3306);
        assertTrue(runningService.isRunning());
    }

    @Test
    public void testNetworkInfoModelDefault() {
        NetworkInfo net = NetworkInfo.createDefault();
        assertNotNull(net);
        assertEquals("Unavailable", net.getLocalAddress());
        assertEquals("Not Configured", net.getPublicAddress());
        assertEquals("Not Configured", net.getTunnelStatus());
        assertFalse(net.isConnected());
    }

    @Test
    public void testNetworkInfoConnectedState() {
        NetworkInfo net = new NetworkInfo("192.168.1.50", "edge.example.com", "Active");
        assertEquals("192.168.1.50", net.getLocalAddress());
        assertEquals("edge.example.com", net.getPublicAddress());
        assertEquals("Active", net.getTunnelStatus());
        assertTrue(net.isConnected());
    }

    @Test
    public void testMonitoringDataModelDefault() {
        MonitoringData data = MonitoringData.createUnmonitoredDefault();
        assertNotNull(data);
        assertFalse(data.isMonitored());
        assertEquals("— %", data.getCpuUsageFormatted());
        assertEquals("— / — GB", data.getMemoryUsageFormatted());
        assertEquals("— °C", data.getTemperatureFormatted());
        assertEquals("— %", data.getBatteryLevelFormatted());
    }

    @Test
    public void testMonitoringDataActiveState() {
        MonitoringData data = new MonitoringData(15.5, 45.0, 36.5, 85, true);
        assertTrue(data.isMonitored());
        assertEquals("15.5%", data.getCpuUsageFormatted());
        assertEquals("45.0%", data.getMemoryUsageFormatted());
        assertEquals("36.5°C", data.getTemperatureFormatted());
        assertEquals("85%", data.getBatteryLevelFormatted());
    }

    @Test
    public void testAltoStatusModelDefault() {
        AltoStatus alto = AltoStatus.createDefault();
        assertNotNull(alto);
        assertFalse(alto.isEnabled());
        assertEquals("Not Active", alto.getState());
        assertEquals(0.0, alto.getLoadScore(), 0.001);
        assertEquals("Standby (Awaiting Server)", alto.getPolicy());
    }

    @Test
    public void testServerControlManagerSingletonAndOperations() {
        ServerControlService service1 = ServerControlManager.getInstance();
        ServerControlService service2 = ServerControlManager.getInstance();
        assertSame(service1, service2);

        // Verify status queries return accurate Day 2 uninitialized baseline
        ServerStatus status = service1.getServerStatus();
        assertNotNull(status);
        assertEquals(ServerConnectionState.NOT_CONFIGURED, status.getStatus());
        assertEquals(ServerConnectionState.NOT_CONFIGURED, service1.getConnectionState());

        // Verify baseline services list
        List<ServiceInfo> services = service1.getServices();
        assertNotNull(services);
        assertEquals(3, services.size());
        assertEquals("Nginx Web Server", services.get(0).getName());
        assertEquals("MariaDB Database Engine", services.get(1).getName());
        assertEquals("Cloudflare Tunnel Daemon", services.get(2).getName());

        // Verify operations return safe unimplemented indicators in Day 2
        assertFalse(service1.startService("nginx"));
        assertFalse(service1.stopService("nginx"));
        assertFalse(service1.restartService("nginx"));

        // Verify applications list
        List<ApplicationInfo> apps = service1.getApplications();
        assertNotNull(apps);
        assertEquals(0, apps.size());

        // Verify network and monitoring
        assertNotNull(service1.getNetworkStatus());
        assertNotNull(service1.getMonitoringData());
        assertNotNull(service1.getAltoStatus());
    }

    @Test
    public void testConstantsIntegrity() {
        assertEquals("Ubuntu 22.04 LTS (aarch64)", com.example.maas.util.Constants.TARGET_OS);
        assertEquals("PRoot / Termux Container", com.example.maas.util.Constants.RUNTIME_LAYER);
        assertEquals(8080, com.example.maas.util.Constants.DEFAULT_HTTP_PORT);
        assertEquals(8443, com.example.maas.util.Constants.DEFAULT_HTTPS_PORT);
        assertEquals(3306, com.example.maas.util.Constants.DEFAULT_MYSQL_PORT);
        assertEquals("Not Connected", com.example.maas.util.Constants.STATUS_NOT_CONNECTED);
        assertEquals("Not Configured", com.example.maas.util.Constants.STATUS_NOT_CONFIGURED);
        assertEquals("Not Active", com.example.maas.util.Constants.STATUS_NOT_ACTIVE);
        assertEquals("Not Implemented", com.example.maas.util.Constants.STATUS_NOT_IMPLEMENTED);
    }
}
