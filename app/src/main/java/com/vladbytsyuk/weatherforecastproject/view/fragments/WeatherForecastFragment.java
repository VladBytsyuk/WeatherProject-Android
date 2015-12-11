package com.vladbytsyuk.weatherforecastproject.view.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vladbytsyuk.weatherforecastproject.controller.DBManager;
import com.vladbytsyuk.weatherforecastproject.R;
import com.vladbytsyuk.weatherforecastproject.controller.NetworkHelper;
import com.vladbytsyuk.weatherforecastproject.controller.adapters.WeatherForecastAdapter;
import com.vladbytsyuk.weatherforecastproject.model.WeatherForecast;
import com.vladbytsyuk.weatherforecastproject.controller.PreferencesHelper;

/**
 * Created by VladBytsyuk on 22.11.2015.
 */
public class WeatherForecastFragment extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener {
    private Context context;
    private DBManager dbManager;
    private TextView textViewCity;
    private ListView listView;
    private WeatherForecastAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    public interface OnItemPressed {
        void itemPressed(WeatherForecast weatherForecast);
    }
    private OnItemPressed listenerItemPressed;

    public void setOnItemPressedListener(OnItemPressed listenerItemPressed) {
        this.listenerItemPressed = listenerItemPressed;
    }

    public void removeOnItemPressedListener() {
        this.listenerItemPressed = null;
    }


    public interface OnRefresh {
        void showNetworkIssuesSnackbar();
    }
    private OnRefresh listenerRefresh;

    public void setOnRefreshListener(OnRefresh listenerRefresh) {
        this.listenerRefresh = listenerRefresh;
    }

    public void removeOnRefreshListener() {
        this.listenerRefresh = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather_forecast, container, false);

        context = getActivity();
        dbManager = new DBManager(context);
        if (dbManager.isDBEmpty())
            dbManager.downloadWeather();

        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefreshLayoutWeatherForecast);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_blue_dark,
                android.R.color.holo_blue_bright);

        rootView = initListView(rootView);
        rootView = initTextView(rootView);

        return rootView;
    }

    private View initListView(View rootView) {
        listView = (ListView) rootView.findViewById(R.id.listViewWeatherForecast);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listenerItemPressed != null)
                    listenerItemPressed.itemPressed(adapter.getItem(position));
            }
        });
        adapter = new WeatherForecastAdapter(context, dbManager.getWeatherForecasts());
        listView.setAdapter(adapter);
        return rootView;
    }

    private View initTextView(View rootView) {
        textViewCity = (TextView) rootView.findViewById(R.id.textViewWeatherForecastCity);
        textViewCity.setText(PreferencesHelper.getPreference(R.string.city));
        return rootView;
    }

    public void refresh() {
        if (NetworkHelper.isNetworkAvailable()) {
            dbManager.drop();
            dbManager.downloadWeather();
            adapter = new WeatherForecastAdapter(context, dbManager.getWeatherForecasts());
            listView.setAdapter(adapter);
        } else  if (listenerRefresh != null) {
            listenerRefresh.showNetworkIssuesSnackbar();
        }
    }

    @Override
    public void onRefresh() {
        refresh();
        swipeRefreshLayout.setRefreshing(false);
    }
}
