package com.example.maas.ui.logs;

import com.example.maas.R;
import com.example.maas.ui.placeholder.BasePlaceholderFragment;

public class LogsFragment extends BasePlaceholderFragment {
    public static LogsFragment newInstance() {
        LogsFragment fragment = new LogsFragment();
        fragment.setArguments(BasePlaceholderFragment.newInstance(
                "Server & Access Logs",
                "Nginx & Syslog Telemetry",
                "Displays real-time streaming logs from Nginx access/error logs, Ubuntu syslog, and tunnel connections for debugging and auditability.",
                "Status: Awaiting Log Streaming Pipeline",
                R.drawable.ic_logs
        ).getArguments());
        return fragment;
    }
}
