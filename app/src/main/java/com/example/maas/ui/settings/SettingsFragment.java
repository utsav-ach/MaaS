package com.example.maas.ui.settings;

import com.example.maas.R;
import com.example.maas.ui.placeholder.BasePlaceholderFragment;

/**
 * Fragment representing the System Configuration module in MaaS.
 * Architectural placeholder for Day 2.
 */
public class SettingsFragment extends BasePlaceholderFragment {

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(BasePlaceholderFragment.newInstance(
                "Settings",
                "MaaS Configuration",
                "\"System Configuration & Runtime Settings\"\n\nConfigures server listener ports, storage paths, Cloudflare credentials, ALTO thermal trip limits, and wake lock / background service policies.",
                "Status: Not Implemented",
                R.drawable.ic_settings
        ).getArguments());
        return fragment;
    }
}
