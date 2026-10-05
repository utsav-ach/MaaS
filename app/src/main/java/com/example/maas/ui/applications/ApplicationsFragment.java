package com.example.maas.ui.applications;

import com.example.maas.R;
import com.example.maas.ui.placeholder.BasePlaceholderFragment;

/**
 * Fragment representing the Hosted Applications module in MaaS.
 * Architectural placeholder for Day 2.
 */
public class ApplicationsFragment extends BasePlaceholderFragment {

    public static ApplicationsFragment newInstance() {
        ApplicationsFragment fragment = new ApplicationsFragment();
        fragment.setArguments(BasePlaceholderFragment.newInstance(
                "Applications",
                "Web Hosting",
                "\"Hosted Applications & Web Stacks\"\n\nProvides deployment, lifecycle management, and virtual host routing for web applications (static websites, PHP scripts, Node.js applications) hosted directly on the mobile edge node.",
                "Status: Not Implemented",
                R.drawable.ic_apps
        ).getArguments());
        return fragment;
    }
}
