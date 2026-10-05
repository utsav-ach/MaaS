package com.example.maas.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.maas.MainActivity;
import com.example.maas.R;
import com.example.maas.model.AltoStatus;
import com.example.maas.model.ApplicationInfo;
import com.example.maas.model.MonitoringData;
import com.example.maas.model.NetworkInfo;
import com.example.maas.model.ServerConnectionState;
import com.example.maas.model.ServerStatus;
import com.example.maas.model.ServiceInfo;
import com.example.maas.service.ServerControlManager;
import com.example.maas.service.ServerControlService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;

/**
 * Main Technical Dashboard for Mobile-as-a-Server (MaaS).
 * Displays server status, static baseline hardware metrics, services, network, and ALTO states.
 * Connects cleanly through the ServerControlService abstraction layer.
 */
public class DashboardFragment extends Fragment {

    private TextView tvServerStatusBadge;
    private TextView tvServerStatusHeadline;
    private TextView tvServerStatusDesc;
    private MaterialButton btnServerAction;

    private TextView tvMetricCpuVal;
    private TextView tvMetricCpuState;
    private TextView tvMetricRamVal;
    private TextView tvMetricRamState;
    private TextView tvMetricTempVal;
    private TextView tvMetricTempState;
    private TextView tvMetricBatteryVal;
    private TextView tvMetricBatteryState;

    private TextView tvServicesActiveVal;
    private TextView tvServicesAppsVal;

    private TextView tvNetworkLocalIp;
    private TextView tvNetworkTunnel;
    private TextView tvNetworkStatus;

    private TextView tvAltoStatusBadge;

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initViews(root);
        bindInitialState();
        setupListeners(root);
        return root;
    }

    private void initViews(@NonNull View root) {
        tvServerStatusBadge = root.findViewById(R.id.tv_server_status_badge);
        tvServerStatusHeadline = root.findViewById(R.id.tv_server_status_headline);
        tvServerStatusDesc = root.findViewById(R.id.tv_server_status_desc);
        btnServerAction = root.findViewById(R.id.btn_server_action);

        tvMetricCpuVal = root.findViewById(R.id.tv_metric_cpu_val);
        tvMetricCpuState = root.findViewById(R.id.tv_metric_cpu_state);
        tvMetricRamVal = root.findViewById(R.id.tv_metric_ram_val);
        tvMetricRamState = root.findViewById(R.id.tv_metric_ram_state);
        tvMetricTempVal = root.findViewById(R.id.tv_metric_temp_val);
        tvMetricTempState = root.findViewById(R.id.tv_metric_temp_state);
        tvMetricBatteryVal = root.findViewById(R.id.tv_metric_battery_val);
        tvMetricBatteryState = root.findViewById(R.id.tv_metric_battery_state);

        tvServicesActiveVal = root.findViewById(R.id.tv_services_active_val);
        tvServicesAppsVal = root.findViewById(R.id.tv_services_apps_val);

        tvNetworkLocalIp = root.findViewById(R.id.tv_network_local_ip);
        tvNetworkTunnel = root.findViewById(R.id.tv_network_tunnel);
        tvNetworkStatus = root.findViewById(R.id.tv_network_status);

        tvAltoStatusBadge = root.findViewById(R.id.tv_alto_status_badge);
    }

    /**
     * Binds model state via the ServerControlService abstraction layer.
     * Keeps the UI completely decoupled from server execution details.
     */
    public void bindInitialState() {
        ServerControlService serverService = ServerControlManager.getInstance();

        ServerStatus serverStatus = serverService.getServerStatus();
        tvServerStatusBadge.setText(serverStatus.getStatus().getLabel().toUpperCase());
        tvServerStatusHeadline.setText(serverStatus.getStatus() == ServerConnectionState.CONNECTED ? "Server Online" : "Currently Offline / Not Connected");
        tvServerStatusDesc.setText(serverStatus.getDescription());

        MonitoringData metrics = serverService.getMonitoringData();
        tvMetricCpuVal.setText(metrics.getCpuUsageFormatted());
        tvMetricCpuState.setText(metrics.isMonitored() ? "Active" : "Offline");
        tvMetricRamVal.setText(metrics.getMemoryUsageFormatted());
        tvMetricRamState.setText(metrics.isMonitored() ? "Active" : "Standby");
        tvMetricTempVal.setText(metrics.getTemperatureFormatted());
        tvMetricTempState.setText(metrics.isMonitored() ? "Active" : "Unmonitored");
        tvMetricBatteryVal.setText(metrics.getBatteryLevelFormatted());
        tvMetricBatteryState.setText(metrics.isMonitored() ? "Active" : "Standby");

        int runningServices = 0;
        for (ServiceInfo service : serverService.getServices()) {
            if (service.isRunning()) {
                runningServices++;
            }
        }
        tvServicesActiveVal.setText(runningServices + " Running");

        int runningApps = 0;
        for (ApplicationInfo app : serverService.getApplications()) {
            if (app.isRunning()) {
                runningApps++;
            }
        }
        tvServicesAppsVal.setText(runningApps + " Deployed");

        NetworkInfo network = serverService.getNetworkStatus();
        tvNetworkLocalIp.setText(network.getLocalAddress());
        tvNetworkTunnel.setText(network.getPublicAddress());
        tvNetworkStatus.setText(network.isConnected() ? "Connected" : "Disconnected");

        AltoStatus alto = serverService.getAltoStatus();
        tvAltoStatusBadge.setText(alto.getState());
    }

    private void setupListeners(@NonNull View root) {
        btnServerAction.setOnClickListener(v -> {
            Toast.makeText(
                    requireContext(),
                    "MaaS: Server daemon integration scheduled for upcoming chunks.",
                    Toast.LENGTH_LONG
            ).show();
        });

        // Quick navigation chips to future module placeholders
        setupChipNavigation(root, R.id.chip_module_server, R.id.menu_server);
        setupChipNavigation(root, R.id.chip_module_apps, R.id.menu_apps);
        setupChipNavigation(root, R.id.chip_module_services, R.id.menu_services);
        setupChipNavigation(root, R.id.chip_module_network, R.id.menu_network);
        setupChipNavigation(root, R.id.chip_module_monitoring, R.id.menu_monitoring);
        setupChipNavigation(root, R.id.chip_module_alto, R.id.menu_alto);
        setupChipNavigation(root, R.id.chip_module_logs, R.id.menu_logs);
        setupChipNavigation(root, R.id.chip_module_settings, R.id.menu_settings);
    }

    private void setupChipNavigation(@NonNull View root, int chipId, int menuItemId) {
        Chip chip = root.findViewById(chipId);
        if (chip != null) {
            chip.setOnClickListener(v -> {
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).navigateToModule(menuItemId);
                }
            });
        }
    }
}
