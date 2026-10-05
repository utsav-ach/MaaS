package com.example.maas.ui.services;

import com.example.maas.R;
import com.example.maas.ui.placeholder.BasePlaceholderFragment;

/**
 * Fragment representing the Services & Daemons module in MaaS.
 * Architectural placeholder for Day 2.
 */
public class ServicesFragment extends BasePlaceholderFragment {

    public static ServicesFragment newInstance() {
        ServicesFragment fragment = new ServicesFragment();
        fragment.setArguments(BasePlaceholderFragment.newInstance(
                "Services",
                "Background Daemons",
                "\"Server Daemons & System Services\"\n\nControls and inspects core server background daemons including Nginx reverse proxy, MariaDB database server, and Cloudflare Tunnel worker processes.",
                "Status: Not Implemented",
                R.drawable.ic_services
        ).getArguments());
        return fragment;
    }
}
