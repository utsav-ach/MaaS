package com.example.maas.service;

import com.example.maas.model.AltoStatus;
import com.example.maas.model.ApplicationInfo;
import com.example.maas.model.MonitoringData;
import com.example.maas.model.NetworkInfo;
import com.example.maas.model.ServerConnectionState;
import com.example.maas.model.ServerStatus;
import com.example.maas.model.ServiceInfo;
import java.util.List;

/**
 * Control abstraction interface for MaaS Linux server operations.
 * Decouples the Android UI layer from future Termux/PRoot Linux daemon execution.
 */
public interface ServerControlService {

    /**
     * Retrieves the overall status of the Linux server environment.
     */
    ServerStatus getServerStatus();

    /**
     * Starts a designated server daemon or background process.
     *
     * @param serviceName Name of the service (e.g., "nginx", "mariadb", "cloudflared")
     * @return true if successfully initiated, false otherwise
     */
    boolean startService(String serviceName);

    /**
     * Stops a running server daemon or background process.
     *
     * @param serviceName Name of the service
     * @return true if successfully stopped, false otherwise
     */
    boolean stopService(String serviceName);

    /**
     * Restarts a designated server daemon.
     *
     * @param serviceName Name of the service
     * @return true if successfully restarted, false otherwise
     */
    boolean restartService(String serviceName);

    /**
     * Lists all registered/hosted web applications on the server.
     */
    List<ApplicationInfo> getApplications();

    /**
     * Lists all registered server daemons and background services.
     */
    List<ServiceInfo> getServices();

    /**
     * Retrieves current network and tunnel configuration status.
     */
    NetworkInfo getNetworkStatus();

    /**
     * Retrieves system and hardware telemetry data.
     */
    MonitoringData getMonitoringData();

    /**
     * Retrieves ALTO load throttling and traffic optimizer state.
     */
    AltoStatus getAltoStatus();

    /**
     * Retrieves the raw connection state to the server daemon layer.
     */
    ServerConnectionState getConnectionState();
}
