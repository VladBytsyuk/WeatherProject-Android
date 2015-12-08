package com.vladbytsyuk.weatherforecastproject.view.activities;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
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
import com.vladbytsyuk.weatherforecastproject.controller.DBManager;
import com.vladbytsyuk.weatherforecastproject.controller.async_task.JSONDownloader;
import com.vladbytsyuk.weatherforecastproject.model.DetailWeatherForecast;
import com.vladbytsyuk.weatherforecastproject.model.WeatherForecast;
import com.vladbytsyuk.weatherforecastproject.view.FormatWeather;
import com.vladbytsyuk.weatherforecastproject.view.fragments.DetailWeatherForecastFragment;
import com.vladbytsyuk.weatherforecastproject.view.fragments.InfoFragment;
import com.vladbytsyuk.weatherforecastproject.view.fragments.SettingsFragment;
import com.vladbytsyuk.weatherforecastproject.view.fragments.WeatherForecastFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, WeatherForecastFragment.OnItemPressed {

    private Boolean exitNow;

    private SettingsFragment settingsFragment;
    private InfoFragment infoFragment;
    private WeatherForecastFragment weatherForecastFragment;
    private DetailWeatherForecastFragment detailWeatherForecastFragment;
    private Boolean isWeatherForecastFragmentActive;

    private DBManager dbManager;

/* ============================================ onCreate ======================================== */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarAndNavigationDrawerInit();

        exitNow = false;
        FormatWeather.setContext(this);

        setPreference(R.string.metric, getString(R.string.celsium));

        settingsFragment = new SettingsFragment();
        infoFragment = new InfoFragment();
        weatherForecastFragment = new WeatherForecastFragment();
        detailWeatherForecastFragment = new DetailWeatherForecastFragment();

        dbManager = new DBManager(this);

        if (getPreference(R.string.city) == null) setPreference(R.string.city, "Rostov");

        setWeatherForecastFragment();

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


/* ======================================= onBackPressed ======================================== */
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
        if (!isWeatherForecastFragmentActive) {
            setWeatherForecastFragment();
        } else {
            if (exitNow) {
                super.onBackPressed();
            } else {
                Toast.makeText(this, getString(R.string.back), Toast.LENGTH_SHORT).show();
                exitNow = true;
            }
        }
    }


/* ================================= onCreateOptionsMenu ======================================== */
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
                dbManager.drop();
                dbManager.downloadWeather();
                setWeatherForecastFragment();
                break;
            case R.id.nav_settings:
                setFragment(settingsFragment);
                break;
            case R.id.nav_info:
                setFragment(infoFragment);
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


/* ======================================== setFragment ========================================= */
    private void setWeatherForecastFragment() {
        setCurrentFragment(weatherForecastFragment);
        isWeatherForecastFragmentActive = true;
    }

    private void setFragment(Fragment fragment) {
        setCurrentFragment(fragment);
        isWeatherForecastFragmentActive = false;
    }

    private void setCurrentFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.commit();
    }


/* ==================================== Shared Preferences ====================================== */
    private String getPreference(int preference) {
        SharedPreferences settings = this
                .getSharedPreferences(this.getString(R.string.shared_preferences_file_name), Context.MODE_PRIVATE);
        return settings.getString(this.getString(preference), null);
    }

    private void setPreference(int key, String value) {
        SharedPreferences settings = this
                .getSharedPreferences(getString(R.string.shared_preferences_file_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(getString(key), value).apply();
    }

/* ====================================== OnItemPressed ========================================= */
    @Override
    protected void onPause() {
        super.onPause();
        weatherForecastFragment.removeOnItemPressedListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        weatherForecastFragment.setOnItemPressedListener(this);
    }

    public void itemPressed(WeatherForecast weatherForecast) {
        detailWeatherForecastFragment.getWeatherForecast(weatherForecast);
        setFragment(detailWeatherForecastFragment);
    }



}
