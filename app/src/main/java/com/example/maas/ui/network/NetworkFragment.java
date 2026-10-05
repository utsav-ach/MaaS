package com.example.maas.ui.network;

import com.example.maas.R;
import com.example.maas.ui.placeholder.BasePlaceholderFragment;

/**
 * Fragment representing the Network & Cloudflare Tunnel module in MaaS.
 * Architectural placeholder for Day 2.
 */
public class NetworkFragment extends BasePlaceholderFragment {

    public static NetworkFragment newInstance() {
        NetworkFragment fragment = new NetworkFragment();
        fragment.setArguments(BasePlaceholderFragment.newInstance(
                "Network",
                "Ingress & Tunneling",
                "\"Network Interfaces & Cloudflare Tunnel\"\n\nManages local IP routing, interface binding, and Cloudflare Zero Trust Tunnel configuration to securely expose the mobile server to the internet without a public IP.",
                "Status: Not Implemented",
                R.drawable.ic_network
        ).getArguments());
        return fragment;
    }
}
