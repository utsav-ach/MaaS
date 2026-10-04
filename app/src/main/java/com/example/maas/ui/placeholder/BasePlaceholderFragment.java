package com.example.maas.ui.placeholder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.maas.MainActivity;
import com.example.maas.R;
import com.google.android.material.button.MaterialButton;

/**
 * Reusable placeholder fragment for future MaaS modules.
 * Prepares the navigation and screen architecture for subsequent development chunks.
 */
public class BasePlaceholderFragment extends Fragment {
    private static final String ARG_TITLE = "arg_title";
    private static final String ARG_BADGE = "arg_badge";
    private static final String ARG_DESC = "arg_desc";
    private static final String ARG_STATUS = "arg_status";
    private static final String ARG_ICON_RES = "arg_icon_res";

    public static BasePlaceholderFragment newInstance(
            String title,
            String badge,
            String description,
            String statusText,
            @DrawableRes int iconRes) {
        BasePlaceholderFragment fragment = new BasePlaceholderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_BADGE, badge);
        args.putString(ARG_DESC, description);
        args.putString(ARG_STATUS, statusText);
        args.putInt(ARG_ICON_RES, iconRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_module_placeholder, container, false);

        ImageView ivIcon = view.findViewById(R.id.iv_placeholder_icon);
        TextView tvTitle = view.findViewById(R.id.tv_placeholder_title);
        TextView tvBadge = view.findViewById(R.id.tv_placeholder_badge);
        TextView tvDesc = view.findViewById(R.id.tv_placeholder_desc);
        TextView tvStatus = view.findViewById(R.id.tv_placeholder_status);
        MaterialButton btnBack = view.findViewById(R.id.btn_placeholder_back);

        if (getArguments() != null) {
            tvTitle.setText(getArguments().getString(ARG_TITLE, "MaaS Module"));
            tvBadge.setText(getArguments().getString(ARG_BADGE, "Planned"));
            tvDesc.setText(getArguments().getString(ARG_DESC, ""));
            tvStatus.setText(getArguments().getString(ARG_STATUS, ""));
            ivIcon.setImageResource(getArguments().getInt(ARG_ICON_RES, R.drawable.ic_server));
        }

        btnBack.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).navigateToDashboard();
            }
        });

        return view;
    }
}
