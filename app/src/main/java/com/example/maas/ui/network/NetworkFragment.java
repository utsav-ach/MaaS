package com.example.maas.ui.network;

import com.example.maas.R;
import com.example.maas.ui.placeholder.BasePlaceholderFragment;

public class NetworkFragment extends BasePlaceholderFragment {
    public static NetworkFragment newInstance() {
        NetworkFragment fragment = new NetworkFragment();
        fragment.setArguments(BasePlaceholderFragment.newInstance(
                "Network & Cloudflare Tunnel",
                "Edge Routing & Zero Trust",
                "Manages local IP routing, port forwarding, and Cloudflare Tunnel token configuration to expose the edge mobile server safely to the public internet.",
                "Status: Awaiting Tunnel Configuration Layer",
                R.drawable.ic_network
        ).getArguments());
        return fragment;
    }
}
