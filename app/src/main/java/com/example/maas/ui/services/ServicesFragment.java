package com.example.maas.ui.services;

import com.example.maas.R;
import com.example.maas.ui.placeholder.BasePlaceholderFragment;

public class ServicesFragment extends BasePlaceholderFragment {
    public static ServicesFragment newInstance() {
        ServicesFragment fragment = new ServicesFragment();
        fragment.setArguments(BasePlaceholderFragment.newInstance(
                "Services & Daemons",
                "Nginx / Database / Daemons",
                "Controls background server daemons including Nginx reverse proxy, MySQL/MariaDB database server, and Cloudflare tunnel worker processes.",
                "Status: Awaiting Daemon Control Services",
                R.drawable.ic_services
        ).getArguments());
        return fragment;
    }
}
