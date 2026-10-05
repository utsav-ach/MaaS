package com.example.maas.ui.monitoring;

import com.example.maas.R;
import com.example.maas.ui.placeholder.BasePlaceholderFragment;

/**
 * Fragment representing the System & Telemetry Monitoring module in MaaS.
 * Architectural placeholder for Day 2.
 */
public class MonitoringFragment extends BasePlaceholderFragment {

    public static MonitoringFragment newInstance() {
        MonitoringFragment fragment = new MonitoringFragment();
        fragment.setArguments(BasePlaceholderFragment.newInstance(
                "Monitoring",
                "Hardware Telemetry",
                "\"Hardware & System Resource Telemetry\"\n\nCollects real-time hardware telemetry from Android BatteryManager, thermal zones, CPU utilization, and RAM consumption to maintain edge health awareness.",
                "Status: Not Implemented",
                R.drawable.ic_monitoring
        ).getArguments());
        return fragment;
    }
}
