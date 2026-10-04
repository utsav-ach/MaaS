package com.example.maas;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.maas.ui.alto.AltoFragment;
import com.example.maas.ui.apps.ApplicationsFragment;
import com.example.maas.ui.dashboard.DashboardFragment;
import com.example.maas.ui.logs.LogsFragment;
import com.example.maas.ui.monitoring.MonitoringFragment;
import com.example.maas.ui.network.NetworkFragment;
import com.example.maas.ui.server.ServerFragment;
import com.example.maas.ui.services.ServicesFragment;
import com.example.maas.ui.settings.SettingsFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

/**
 * Main Activity for Mobile-as-a-Server (MaaS) Management Application.
 * Coordinates navigation across all 9 core MaaS modules.
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private MaterialToolbar topAppBar;
    private int currentSelectedItemId = R.id.menu_dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        topAppBar = findViewById(R.id.top_app_bar);

        setupToolbar();
        setupNavigation();

        if (savedInstanceState == null) {
            loadFragment(DashboardFragment.newInstance(), getString(R.string.nav_dashboard));
            navigationView.setCheckedItem(R.id.menu_dashboard);
        }
    }

    private void setupToolbar() {
        topAppBar.setNavigationOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        topAppBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_refresh) {
                handleRefreshAction();
                return true;
            }
            return false;
        });
    }

    private void setupNavigation() {
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        navigateToModule(id);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Programmatically navigates to any of the 9 MaaS modules.
     */
    public void navigateToModule(int menuItemId) {
        currentSelectedItemId = menuItemId;
        navigationView.setCheckedItem(menuItemId);

        Fragment fragment;
        String title;

        if (menuItemId == R.id.menu_dashboard) {
            fragment = DashboardFragment.newInstance();
            title = getString(R.string.app_name);
        } else if (menuItemId == R.id.menu_server) {
            fragment = ServerFragment.newInstance();
            title = getString(R.string.nav_server);
        } else if (menuItemId == R.id.menu_apps) {
            fragment = ApplicationsFragment.newInstance();
            title = getString(R.string.nav_apps);
        } else if (menuItemId == R.id.menu_services) {
            fragment = ServicesFragment.newInstance();
            title = getString(R.string.nav_services);
        } else if (menuItemId == R.id.menu_network) {
            fragment = NetworkFragment.newInstance();
            title = getString(R.string.nav_network);
        } else if (menuItemId == R.id.menu_monitoring) {
            fragment = MonitoringFragment.newInstance();
            title = getString(R.string.nav_monitoring);
        } else if (menuItemId == R.id.menu_alto) {
            fragment = AltoFragment.newInstance();
            title = getString(R.string.nav_alto);
        } else if (menuItemId == R.id.menu_logs) {
            fragment = LogsFragment.newInstance();
            title = getString(R.string.nav_logs);
        } else if (menuItemId == R.id.menu_settings) {
            fragment = SettingsFragment.newInstance();
            title = getString(R.string.nav_settings);
        } else {
            fragment = DashboardFragment.newInstance();
            title = getString(R.string.app_name);
        }

        loadFragment(fragment, title);
    }

    /**
     * Returns back to the main dashboard.
     */
    public void navigateToDashboard() {
        navigateToModule(R.id.menu_dashboard);
    }

    private void loadFragment(Fragment fragment, String title) {
        topAppBar.setTitle(title);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    private void handleRefreshAction() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (currentFragment instanceof DashboardFragment) {
            ((DashboardFragment) currentFragment).bindInitialState();
            Toast.makeText(this, "MaaS Dashboard: Baseline status reloaded.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Status refreshed.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else if (currentSelectedItemId != R.id.menu_dashboard) {
            navigateToDashboard();
        } else {
            super.onBackPressed();
        }
    }
}
