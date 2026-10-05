package com.example.maas.service;

import com.example.maas.model.AltoStatus;
import com.example.maas.model.ApplicationInfo;
import com.example.maas.model.MonitoringData;
import com.example.maas.model.NetworkInfo;
import com.example.maas.model.ServerConnectionState;
import com.example.maas.model.ServerStatus;
import com.example.maas.model.ServiceInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Concrete management implementation of ServerControlService.
 * Acts as the centralized bridge between the Android UI and the future Linux server runtime.
 * In Day 2, this provides architectural decoupling with honest disconnected/unconfigured states.
 */
public class ServerControlManager implements ServerControlService {

    private static volatile ServerControlManager instance;

    private final ServerStatus serverStatus;
    private final NetworkInfo networkInfo;
    private final MonitoringData monitoringData;
    private final AltoStatus altoStatus;
    private final List<ServiceInfo> plannedServices;
    private final List<ApplicationInfo> hostedApplications;

    private ServerControlManager() {
        this.serverStatus = ServerStatus.createDefault();
        this.networkInfo = NetworkInfo.createDefault();
        this.monitoringData = MonitoringData.createUnmonitoredDefault();
        this.altoStatus = AltoStatus.createDefault();

        // Baseline service records for planned architecture
        List<ServiceInfo> services = new ArrayList<>();
        services.add(new ServiceInfo("Nginx Web Server", "Stopped", 8080));
        services.add(new ServiceInfo("MariaDB Database Engine", "Stopped", 3306));
        services.add(new ServiceInfo("Cloudflare Tunnel Daemon", "Stopped", 0));
        this.plannedServices = Collections.unmodifiableList(services);

        this.hostedApplications = Collections.emptyList();
    }

    /**
     * Singleton accessor for the server control manager.
     */
    public static ServerControlManager getInstance() {
        if (instance == null) {
            synchronized (ServerControlManager.class) {
                if (instance == null) {
                    instance = new ServerControlManager();
                }
            }
        }
        return instance;
    }

    @Override
    public ServerStatus getServerStatus() {
        return serverStatus;
    }

    @Override
    public boolean startService(String serviceName) {
        // Day 2: Server execution environment is not yet initialized.
        return false;
    }

    @Override
    public boolean stopService(String serviceName) {
        // Day 2: Server execution environment is not yet initialized.
        return false;
    }

    @Override
    public boolean restartService(String serviceName) {
        // Day 2: Server execution environment is not yet initialized.
        return false;
    }

    @Override
    public List<ApplicationInfo> getApplications() {
        return hostedApplications;
    }

    @Override
    public List<ServiceInfo> getServices() {
        return plannedServices;
    }

    @Override
    public NetworkInfo getNetworkStatus() {
        return networkInfo;
    }

    @Override
    public MonitoringData getMonitoringData() {
        return monitoringData;
    }

    @Override
    public AltoStatus getAltoStatus() {
        return altoStatus;
    }

    @Override
    public ServerConnectionState getConnectionState() {
        return serverStatus.getStatus();
    }
}
