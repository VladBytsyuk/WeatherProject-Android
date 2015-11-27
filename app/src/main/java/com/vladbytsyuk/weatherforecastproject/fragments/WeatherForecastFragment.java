package com.vladbytsyuk.weatherforecastproject.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.vladbytsyuk.weatherforecastproject.DetailWeatherForecast;
import com.vladbytsyuk.weatherforecastproject.R;
import com.vladbytsyuk.weatherforecastproject.Temperature;
import com.vladbytsyuk.weatherforecastproject.Time;
import com.vladbytsyuk.weatherforecastproject.WeatherForecast;
import com.vladbytsyuk.weatherforecastproject.adapters.WeatherForecastAdapter;

import java.util.ArrayList;

/**
 * Created by VladBytsyuk on 22.11.2015.
 */
public class WeatherForecastFragment extends Fragment {
    private TextView textViewCity;
    private ListView listView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_weather_forecast ,container, false);

        textViewCity = (TextView) rootView.findViewById(R.id.textViewWeatherForecastCity);
        listView = (ListView) rootView.findViewById(R.id.listViewWeatherForecast);
        WeatherForecastAdapter adapter = new WeatherForecastAdapter(getActivity(), getWeatherForecasts());
        listView.setAdapter(adapter);

        return rootView;
    }

    private ArrayList<WeatherForecast> getWeatherForecasts() {
        ArrayList<WeatherForecast> list = new ArrayList<>();
        list.add(new WeatherForecast(new Temperature(new Float(5), new Float(10), new Float(8), new Float(4)),
                                     new Time("31.12.2015", "Monday"), "ico",
                                     new DetailWeatherForecast(new Float(2), new Float(304.5), new Float(36), new Float(56))));
        list.add(new WeatherForecast(new Temperature(new Float(5), new Float(15), new Float(8), new Float(9)),
                new Time("30.12.2015", "Sunday"), "ico",
                new DetailWeatherForecast(new Float(2), new Float(304.5), new Float(36), new Float(56))));
        return list;
    }
}
