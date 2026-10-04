package com.example.maas.ui.apps;

import com.example.maas.R;
import com.example.maas.ui.placeholder.BasePlaceholderFragment;

public class ApplicationsFragment extends BasePlaceholderFragment {
    public static ApplicationsFragment newInstance() {
        ApplicationsFragment fragment = new ApplicationsFragment();
        fragment.setArguments(BasePlaceholderFragment.newInstance(
                "Hosted Applications",
                "Web Hosts & Stacks",
                "Enables deploying, configuring, and monitoring web applications (static sites, PHP/Node.js apps) hosted directly on the mobile edge server.",
                "Status: Awaiting Application Runtime Layer",
                R.drawable.ic_apps
        ).getArguments());
        return fragment;
    }
}
