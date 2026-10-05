package com.example.maas.ui.apps;

/**
 * @deprecated Use {@link com.example.maas.ui.applications.ApplicationsFragment} instead.
 */
@Deprecated
public class ApplicationsFragment extends com.example.maas.ui.applications.ApplicationsFragment {
    public static ApplicationsFragment newInstance() {
        ApplicationsFragment fragment = new ApplicationsFragment();
        fragment.setArguments(com.example.maas.ui.applications.ApplicationsFragment.newInstance().getArguments());
        return fragment;
    }
}
