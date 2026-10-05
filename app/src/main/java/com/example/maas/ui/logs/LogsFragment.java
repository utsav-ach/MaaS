package com.example.maas.ui.logs;

import com.example.maas.R;
import com.example.maas.ui.placeholder.BasePlaceholderFragment;

/**
 * Fragment representing the Server & Access Logs module in MaaS.
 * Architectural placeholder for Day 2.
 */
public class LogsFragment extends BasePlaceholderFragment {

    public static LogsFragment newInstance() {
        LogsFragment fragment = new LogsFragment();
        fragment.setArguments(BasePlaceholderFragment.newInstance(
                "Logs",
                "System & Daemon Journal",
                "\"Server & Access Log Streaming\"\n\nProvides streaming log inspection for Nginx access and error logs, Linux syslog, and tunnel runtime events for auditability and debugging.",
                "Status: Not Implemented",
                R.drawable.ic_logs
        ).getArguments());
        return fragment;
    }
}
