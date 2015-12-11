package com.vladbytsyuk.weatherforecastproject.controller.async_task;

import android.content.Context;
import android.os.AsyncTask;

import com.vladbytsyuk.weatherforecastproject.R;
import com.vladbytsyuk.weatherforecastproject.model.WeatherForecast;
import com.vladbytsyuk.weatherforecastproject.controller.PreferencesHelper;

import java.util.ArrayList;

/**
 * Created by VladBytsyuk on 29.11.2015.
 */
public class WeatherDownloader extends AsyncTask<Context, Void, ArrayList<WeatherForecast>> {
    private  Context context;

    @Override
    protected ArrayList<WeatherForecast> doInBackground(Context... params) {
        context = params[0];
        String city = PreferencesHelper.getPreference(R.string.city);
        String metric = PreferencesHelper.getPreference(R.string.metric);
        String lang = getString(R.string.lang);
        String jsonFile = JSONDownloader.downloadJSON(city, metric, lang);
        ArrayList<WeatherForecast> weatherForecasts = JSONParser.parseJSON(jsonFile);
        return weatherForecasts;
    }


    private String getString(int id) {
        return context.getResources().getString(id);
    }

}
