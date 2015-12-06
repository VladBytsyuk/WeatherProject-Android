package com.vladbytsyuk.weatherforecastproject.view.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.vladbytsyuk.weatherforecastproject.controller.DBManager;
import com.vladbytsyuk.weatherforecastproject.R;
import com.vladbytsyuk.weatherforecastproject.controller.adapters.WeatherForecastAdapter;
import com.vladbytsyuk.weatherforecastproject.model.WeatherForecast;

/**
 * Created by VladBytsyuk on 22.11.2015.
 */
public class WeatherForecastFragment extends Fragment {
    private Context context;
    private DBManager dbManager;
    private TextView textViewCity;
    private ListView listView;
    private WeatherForecastAdapter adapter;

    public interface OnItemPressed {
        void itemPressed(WeatherForecast weatherForecast);
    }
    private OnItemPressed listener;

    public void setOnItemPressedListener(OnItemPressed listener) {
        this.listener = listener;
    }

    public void removeOnItemPressedListener() {
        this.listener = null;
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
        textViewCity = (TextView) rootView.findViewById(R.id.textViewWeatherForecastCity);
        listView = (ListView) rootView.findViewById(R.id.listViewWeatherForecast);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null)
                    listener.itemPressed(adapter.getItem(position));
            }
        });
        textViewCity.setText(getPreference(R.string.city));
        adapter = new WeatherForecastAdapter(context, dbManager.getWeatherForecasts());
        listView.setAdapter(adapter);

        return rootView;
    }

    private String getPreference(int preference) {
        SharedPreferences settings = getActivity()
                .getSharedPreferences(getActivity().getString(R.string.shared_preferences_file_name), Context.MODE_PRIVATE);
        return settings.getString(getActivity().getString(preference), null);
    }

}
