package com.example.maas.ui.server;

import com.example.maas.R;
import com.example.maas.ui.placeholder.BasePlaceholderFragment;

public class ServerFragment extends BasePlaceholderFragment {
    public static ServerFragment newInstance() {
        ServerFragment fragment = new ServerFragment();
        fragment.setArguments(BasePlaceholderFragment.newInstance(
                "Linux Server Environment",
                "Ubuntu 22.04 LTS / Termux",
                "Houses the isolated Linux container running on Android using PRoot/Termux. In subsequent chunks, this module manages server initialization, environment setup, package management, and daemon lifecycles.",
                "Status: Awaiting Day 2+ Environment Initialization",
                R.drawable.ic_server
        ).getArguments());
        return fragment;
    }
}
