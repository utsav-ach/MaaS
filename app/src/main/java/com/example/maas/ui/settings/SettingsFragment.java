package com.example.maas.ui.settings;

import com.example.maas.R;
import com.example.maas.ui.placeholder.BasePlaceholderFragment;

public class SettingsFragment extends BasePlaceholderFragment {
    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(BasePlaceholderFragment.newInstance(
                "System Configuration",
                "MaaS Preferences & Parameters",
                "Configures server ports, web document roots, Cloudflare Tunnel credentials, ALTO thermal trip limits, and wake lock / background service policies.",
                "Status: Awaiting Config Store & SharedPreferences",
                R.drawable.ic_settings
        ).getArguments());
        return fragment;
    }
}
