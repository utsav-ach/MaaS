package com.example.maas.ui.server;

import com.example.maas.R;
import com.example.maas.ui.placeholder.BasePlaceholderFragment;

/**
 * Fragment representing the Linux Server Environment module in MaaS.
 * Architectural placeholder for Day 2.
 */
public class ServerFragment extends BasePlaceholderFragment {

    public static ServerFragment newInstance() {
        ServerFragment fragment = new ServerFragment();
        fragment.setArguments(BasePlaceholderFragment.newInstance(
                "Server",
                "Linux Server Environment",
                "\"Edge Linux Server Environment & Runtime Host\"\n\nHouses the isolated PRoot/Termux container hosting Ubuntu 22.04 LTS. In upcoming chunks, manages server initialization, environment setup, package management, and daemon lifecycles.",
                "Status: Not Implemented",
                R.drawable.ic_server
        ).getArguments());
        return fragment;
    }
}
