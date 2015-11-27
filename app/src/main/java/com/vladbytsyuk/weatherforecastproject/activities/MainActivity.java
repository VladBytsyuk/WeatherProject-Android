package com.vladbytsyuk.weatherforecastproject.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.vladbytsyuk.weatherforecastproject.R;
import com.vladbytsyuk.weatherforecastproject.fragments.InfoFragment;
import com.vladbytsyuk.weatherforecastproject.fragments.SettingsFragment;
import com.vladbytsyuk.weatherforecastproject.fragments.WeatherForecastFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String LOG_TAG = "ACTIVITY_LOG_TAG";

    private Boolean exitNow;

    private SettingsFragment settingsFragment;
    private String SETTINGS_FRAGMENT_TAG = "SETTINGS_FRAGMENT";
    private Boolean isSettingsFragmentActive;
    private String SETTINGS_FRAGMENT_WAS_CREATED_TAG = "SETTINGS_FRAGMENT_WAS_CREATED";
    private Boolean isSettingsFragmentWasCreated;

    private InfoFragment infoFragment;
    private String INFO_FRAGMENT_TAG = "INFO_FRAGMENT";
    private Boolean isInfoFragmentActive;
    private String INFO_FRAGMENT_WAS_CREATED_TAG = "INFO_FRAGMENT_WAS_CREATED";
    private Boolean isInfoFragmentWasCreated;

    private WeatherForecastFragment weatherForecastFragment;
    private String WEATHER_FORECAST_FRAGMENT_TAG = "WEATHER_FORECAST_FRAGMENT";
    private Boolean isWeatherForecastFragmentActive;
    private String WEATHER_FORECAST_FRAGMENT_WAS_CREATED_TAG = "WEATHER_FORECAST_FRAGMENT_WAS_CREATED";
    private Boolean isWeatherForecastFragmentWasCreated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarAndNavigationDrawerInit();

        exitNow = false;

        fragmentsInfoInit(savedInstanceState);
        fragmentsInit();
        setFirstFragment();
    }

    private void fragmentsInfoInit(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            isSettingsFragmentWasCreated = savedInstanceState.getBoolean(SETTINGS_FRAGMENT_WAS_CREATED_TAG);
            isInfoFragmentWasCreated = savedInstanceState.getBoolean(INFO_FRAGMENT_WAS_CREATED_TAG);
            isWeatherForecastFragmentWasCreated = savedInstanceState.getBoolean(WEATHER_FORECAST_FRAGMENT_WAS_CREATED_TAG);

            isSettingsFragmentActive = savedInstanceState.getBoolean(SETTINGS_FRAGMENT_TAG);
            isInfoFragmentActive = savedInstanceState.getBoolean(INFO_FRAGMENT_TAG);
            isWeatherForecastFragmentActive = savedInstanceState.getBoolean(WEATHER_FORECAST_FRAGMENT_TAG);
        } else {
            isSettingsFragmentWasCreated = false;
            isInfoFragmentWasCreated = false;
            isWeatherForecastFragmentWasCreated = false;

            isSettingsFragmentActive = false;
            isInfoFragmentActive = false;
            isWeatherForecastFragmentActive = false;
        }

        Log.d(LOG_TAG, "onCreate");
        Log.d(LOG_TAG, "    isSettingsActive: " + isSettingsFragmentActive + "  isInfoActive: " + isInfoFragmentActive + "  isWFActive: " + isWeatherForecastFragmentActive);
        Log.d(LOG_TAG, "    isSettingsCreate: " + isSettingsFragmentWasCreated + "  isInfoCreate: " + isInfoFragmentWasCreated + "  isWFCreate: " + isWeatherForecastFragmentWasCreated);
        Log.d(LOG_TAG, "    backStack: " + getFragmentManager().getBackStackEntryCount());
    }

    private void fragmentsInit() {
        if (isSettingsFragmentWasCreated) {
            settingsFragment = (SettingsFragment) getFragmentManager().findFragmentByTag(SETTINGS_FRAGMENT_TAG);
        } else {
            settingsFragment = new SettingsFragment();
        }
        if (isInfoFragmentWasCreated) {
            infoFragment = (InfoFragment) getFragmentManager().findFragmentByTag(INFO_FRAGMENT_TAG);
        } else {
            infoFragment = new InfoFragment();
        }
        if (isWeatherForecastFragmentWasCreated) {
            weatherForecastFragment = (WeatherForecastFragment) getFragmentManager().findFragmentByTag(WEATHER_FORECAST_FRAGMENT_TAG);
        } else {
            weatherForecastFragment = new WeatherForecastFragment();
        }
        isSettingsFragmentWasCreated = true;
        isInfoFragmentWasCreated = true;
        isWeatherForecastFragmentWasCreated = true;
    }

    private void setFirstFragment() {
        if (isSettingsFragmentActive) {
            setCurrentFragment(R.id.main_container, settingsFragment,
                    SETTINGS_FRAGMENT_TAG, true);
        } else if (isInfoFragmentActive) {
            setCurrentFragment(R.id.main_container, infoFragment,
                    INFO_FRAGMENT_TAG, true);
        } else {
            setCurrentFragment(R.id.main_container, weatherForecastFragment,
                WEATHER_FORECAST_FRAGMENT_TAG, false);
            isWeatherForecastFragmentActive = true;
        }
    }

    private void toolbarAndNavigationDrawerInit() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(SETTINGS_FRAGMENT_WAS_CREATED_TAG, isSettingsFragmentWasCreated);
        outState.putBoolean(INFO_FRAGMENT_WAS_CREATED_TAG, isInfoFragmentWasCreated);
        outState.putBoolean(WEATHER_FORECAST_FRAGMENT_WAS_CREATED_TAG, isWeatherForecastFragmentWasCreated);

        outState.putBoolean(SETTINGS_FRAGMENT_TAG, isSettingsFragmentActive);
        outState.putBoolean(INFO_FRAGMENT_TAG, isInfoFragmentActive);
        outState.putBoolean(WEATHER_FORECAST_FRAGMENT_TAG, isWeatherForecastFragmentActive);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            onFragmentBackPressed();
        }
    }

    private void onFragmentBackPressed() {
        Log.d(LOG_TAG, "onBack");
        Log.d(LOG_TAG, "    preBackStack: " + getFragmentManager().getBackStackEntryCount());
        if (!isWeatherForecastFragmentActive) {
            backToWeatherForecastFragment();
        } else {
            if (exitNow) {
                super.onBackPressed();
            } else {
                Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
                exitNow = true;
            }
        }

        Log.d(LOG_TAG, "    isSettingsActive: " + isSettingsFragmentActive + "  isInfoActive: " + isInfoFragmentActive + "  isWFActive: " + isWeatherForecastFragmentActive);
        Log.d(LOG_TAG, "    isSettingsCreate: " + isSettingsFragmentWasCreated + "  isInfoCreate: " + isInfoFragmentWasCreated + "  isWFCreate: " + isWeatherForecastFragmentWasCreated);
        Log.d(LOG_TAG, "    postBackStack: " + getFragmentManager().getBackStackEntryCount());
    }

    private void backToWeatherForecastFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();

        isSettingsFragmentActive = false;
        isInfoFragmentActive = false;
        isWeatherForecastFragmentActive = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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

                break;
            case R.id.nav_settings:
                if (!isSettingsFragmentActive)
                    settingsNavigationItemSelected();
                break;
            case R.id.nav_info:
                if (!isInfoFragmentActive)
                    infoNavigationItemSelected();
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void settingsNavigationItemSelected() {
        setCurrentFragment(R.id.main_container, settingsFragment,
                SETTINGS_FRAGMENT_TAG, true);

        isSettingsFragmentActive = true;
        isInfoFragmentActive = false;
        isWeatherForecastFragmentActive = false;

        Log.d(LOG_TAG, "onSettings");
        Log.d(LOG_TAG, "    isSettingsActive: " + isSettingsFragmentActive + "  isInfoActive: " + isInfoFragmentActive + "  isWFActive: " + isWeatherForecastFragmentActive);
        Log.d(LOG_TAG, "    isSettingsCreate: " + isSettingsFragmentWasCreated + "  isInfoCreate: " + isInfoFragmentWasCreated + "  isWFCreate: " + isWeatherForecastFragmentWasCreated);
        Log.d(LOG_TAG, "    backStack: " + getFragmentManager().getBackStackEntryCount());
    }

    private void infoNavigationItemSelected() {
        setCurrentFragment(R.id.main_container, infoFragment,
                INFO_FRAGMENT_TAG, true);

        isSettingsFragmentActive = false;
        isInfoFragmentActive = true;
        isWeatherForecastFragmentActive = false;

        Log.d(LOG_TAG, "onInfo");
        Log.d(LOG_TAG, "    isSettingsActive: " + isSettingsFragmentActive + "  isInfoActive: " + isInfoFragmentActive + "  isWFActive: " + isWeatherForecastFragmentActive);
        Log.d(LOG_TAG, "    isSettingsCreate: " + isSettingsFragmentWasCreated + "  isInfoCreate: " + isInfoFragmentWasCreated + "  isWFCreate: " + isWeatherForecastFragmentWasCreated);
        Log.d(LOG_TAG, "    backStack: " + getFragmentManager().getBackStackEntryCount());
    }

    private void setCurrentFragment(int container, Fragment fragment,
                                    String tag, Boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(container, fragment, tag);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();
    }

}
