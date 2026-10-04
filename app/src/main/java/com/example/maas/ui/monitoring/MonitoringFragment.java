package com.example.maas.ui.monitoring;

import com.example.maas.R;
import com.example.maas.ui.placeholder.BasePlaceholderFragment;

public class MonitoringFragment extends BasePlaceholderFragment {
    public static MonitoringFragment newInstance() {
        MonitoringFragment fragment = new MonitoringFragment();
        fragment.setArguments(BasePlaceholderFragment.newInstance(
                "Live System Telemetry",
                "Thermal & Resource Sensors",
                "Reads real-time hardware telemetry from Android BatteryManager, thermal zones, /proc/stat CPU load, and RAM usage for visualization and feeding into ALTO.",
                "Status: Awaiting Hardware Telemetry Collector",
                R.drawable.ic_monitoring
        ).getArguments());
        return fragment;
    }
}
