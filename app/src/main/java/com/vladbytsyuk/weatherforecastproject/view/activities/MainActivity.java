package com.vladbytsyuk.weatherforecastproject.view.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.vladbytsyuk.weatherforecastproject.R;
import com.vladbytsyuk.weatherforecastproject.controller.NetworkHelper;
import com.vladbytsyuk.weatherforecastproject.controller.ResourceHelper;
import com.vladbytsyuk.weatherforecastproject.model.WeatherForecast;
import com.vladbytsyuk.weatherforecastproject.view.FormatWeather;
import com.vladbytsyuk.weatherforecastproject.controller.PreferencesHelper;
import com.vladbytsyuk.weatherforecastproject.view.fragments.DetailWeatherForecastFragment;
import com.vladbytsyuk.weatherforecastproject.view.fragments.InfoFragment;
import com.vladbytsyuk.weatherforecastproject.view.fragments.SettingsFragment;
import com.vladbytsyuk.weatherforecastproject.view.fragments.WeatherForecastFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
                   WeatherForecastFragment.OnItemPressed,
                   WeatherForecastFragment.OnRefresh {

    private Boolean exitNow;

    private SettingsFragment settingsFragment;
    private InfoFragment infoFragment;
    private WeatherForecastFragment weatherForecastFragment;
    private DetailWeatherForecastFragment detailWeatherForecastFragment;

    private Boolean isSettingsFragmentActive;
    private Boolean isWeatherForecastFragmentActive;

/* ============================================ onCreate ======================================== */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exitNow = false;

        toolbarAndNavigationDrawerInit();
        setContexts();
        initSharedPreferences();
        initFragments();

        setWeatherForecastFragment();

    }

    private void toolbarAndNavigationDrawerInit() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setContexts() {
        FormatWeather.setContext(this);
        PreferencesHelper.setContext(this);
        NetworkHelper.setContext(this);
        ResourceHelper.setContext(this);
    }

    private void initSharedPreferences() {
        if (PreferencesHelper.getPreference(R.string.metric) == null) {
            PreferencesHelper.setPreference(R.string.metric, getString(R.string.celsium));
        }
        if (PreferencesHelper.getPreference(R.string.city) == null) {
            if (getString(R.string.lang).equals("ru")) {
                PreferencesHelper.setPreference(R.string.city, "Ростов-на-Дону");
            } else {
                PreferencesHelper.setPreference(R.string.city, "Rostov");
            }
        }
    }

    private void initFragments() {
        settingsFragment = new SettingsFragment();
        infoFragment = new InfoFragment();
        weatherForecastFragment = new WeatherForecastFragment();
        detailWeatherForecastFragment = new DetailWeatherForecastFragment();
    }


/* ======================================= onBackPressed ======================================== */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            exitNow = false;
            drawer.closeDrawer(GravityCompat.START);
        } else {
            onFragmentBackPressed();
        }
    }

    private void onFragmentBackPressed() {
        if (!isWeatherForecastFragmentActive) {
            if (isSettingsFragmentActive) {
                refresh();
            } else {
                setWeatherForecastFragment();
            }
        } else {
            if (exitNow) {
                super.onBackPressed();
            } else {
                Toast.makeText(this, getString(R.string.back), Toast.LENGTH_SHORT).show();
                exitNow = true;
            }
        }
    }



/* =============================== onNavigationItemSelected ===================================== */


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }

        switch (id) {
            case R.id.nav_refresh:
                refresh();
                break;
            case R.id.nav_settings:
                setSettingsFragment();
                break;
            case R.id.nav_info:
                setFragment(infoFragment);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void refresh() {
        setWeatherForecastFragment();
        weatherForecastFragment.refresh();
    }


/* ======================================== setFragment ========================================= */
    private void setWeatherForecastFragment() {
        setCurrentFragment(weatherForecastFragment);
        isWeatherForecastFragmentActive = true;
        isSettingsFragmentActive = false;
    }

    private void setSettingsFragment() {
        setCurrentFragment(settingsFragment);
        isSettingsFragmentActive = true;
        isWeatherForecastFragmentActive = false;
    }

    private void setFragment(Fragment fragment) {
        setCurrentFragment(fragment);
        isWeatherForecastFragmentActive = false;
        isSettingsFragmentActive = false;
    }

    private void setCurrentFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
        exitNow = false;
    }


/* ====================================== Interfaces ============================================ */
    @Override
    protected void onPause() {
        super.onPause();
        weatherForecastFragment.removeOnItemPressedListener();
        weatherForecastFragment.removeOnRefreshListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        weatherForecastFragment.setOnItemPressedListener(this);
        weatherForecastFragment.setOnRefreshListener(this);
    }

    public void itemPressed(WeatherForecast weatherForecast) {
        detailWeatherForecastFragment.getWeatherForecast(weatherForecast);
        setFragment(detailWeatherForecastFragment);
    }

    public void showNetworkIssuesSnackbar() {
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.main_container),
                        R.string.download_error, Snackbar.LENGTH_LONG)
                .setAction(R.string.update, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refresh();
                    }
                });
        snackbar.show();
    }



}
