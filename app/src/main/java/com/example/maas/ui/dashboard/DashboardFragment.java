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
import com.example.maas.model.AltoState;
import com.example.maas.model.NetworkState;
import com.example.maas.model.ServerStatusState;
import com.example.maas.model.ServicesState;
import com.example.maas.model.SystemMetricsState;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;

/**
 * Main Technical Dashboard for Mobile-as-a-Server (MaaS).
 * Displays server status, static baseline hardware metrics, services, network, and ALTO states.
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
     * Binds Day 1 initial/static model state cleanly.
     * Ready to receive live observables or callbacks in subsequent chunks.
     */
    public void bindInitialState() {
        ServerStatusState serverState = ServerStatusState.createOfflineDefault();
        tvServerStatusBadge.setText(serverState.getBadgeText());
        tvServerStatusHeadline.setText(serverState.getStatusHeadline());
        tvServerStatusDesc.setText(serverState.getStatusDescription());

        SystemMetricsState metrics = SystemMetricsState.createPlaceholderDefault();
        tvMetricCpuVal.setText(metrics.getCpuValue());
        tvMetricCpuState.setText(metrics.getCpuState());
        tvMetricRamVal.setText(metrics.getRamValue());
        tvMetricRamState.setText(metrics.getRamState());
        tvMetricTempVal.setText(metrics.getTempValue());
        tvMetricTempState.setText(metrics.getTempState());
        tvMetricBatteryVal.setText(metrics.getBatteryValue());
        tvMetricBatteryState.setText(metrics.getBatteryState());

        ServicesState services = ServicesState.createInitialDefault();
        tvServicesActiveVal.setText(services.getActiveServicesCount() + " Running");
        tvServicesAppsVal.setText(services.getDeployedAppsCount() + " Deployed");

        NetworkState network = NetworkState.createInitialDefault();
        tvNetworkLocalIp.setText(network.getLocalIpAddress());
        tvNetworkTunnel.setText(network.getPublicTunnelUrl());
        tvNetworkStatus.setText(network.getConnectionStatus());

        AltoState alto = AltoState.createInitialDefault();
        tvAltoStatusBadge.setText(alto.getStatus());
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
