package com.vladbytsyuk.weatherforecastproject.controller.async_task;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.vladbytsyuk.weatherforecastproject.R;
import com.vladbytsyuk.weatherforecastproject.model.WeatherForecast;

import java.util.ArrayList;

/**
 * Created by VladBytsyuk on 29.11.2015.
 */
public class WeatherDownloader extends AsyncTask<Context, Void, ArrayList<WeatherForecast>> {
    private  Context context;

    @Override
    protected ArrayList<WeatherForecast> doInBackground(Context... params) {
        context = params[0];
        String city = getPreference(R.string.city);
        String metric = getPreference(R.string.metric);
        String lang = getString(R.string.lang);
        String jsonFile = JSONDownloader.downloadJSON(city, metric, lang);
        ArrayList<WeatherForecast> weatherForecasts = JSONParser.parseJSON(jsonFile);
        return weatherForecasts;
    }

    private String getPreference(int preference) {
        SharedPreferences settings = context.getSharedPreferences(getString(R.string.shared_preferences_file_name), Context.MODE_PRIVATE);
        return settings.getString(getString(preference), null);
    }

    private String getString(int id) {
        return context.getResources().getString(id);
    }

}
