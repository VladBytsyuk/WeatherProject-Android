package com.vladbytsyuk.weatherforecastproject.controller;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.vladbytsyuk.weatherforecastproject.R;
import com.vladbytsyuk.weatherforecastproject.model.WeatherForecast;

import java.util.ArrayList;

/**
 * Created by BVS on 28.11.2015.
 */
public class WeatherDownloader extends AsyncTask<Context, Void, Void> {
    @Override
    protected Void doInBackground(Context... params) {
        Context context = params[0];
        String city = getPreference(context, R.string.city);
        String metric = getPreference(context, R.string.metric);
        String jsonFile = JSONDownloader.downloadJSON(city, metric);
        ArrayList<WeatherForecast> weatherForecasts = JSONParser.parseJSON(jsonFile);
        fillDataBase(context, weatherForecasts);
        return null;
    }

    private String getPreference(Context context, int preference) {
        SharedPreferences settings = context.getSharedPreferences(context.getString(R.string.shared_preferences_file_name), Context.MODE_PRIVATE);
        return settings.getString(context.getString(preference), null);
    }

    private void fillDataBase(Context context, ArrayList<WeatherForecast> weatherForecasts) {
        for (WeatherForecast x : weatherForecasts) {
            ContentValues cv = new ContentValues();

            cv.put(getString(context, R.string.day), x.getDay());
            cv.put(getString(context, R.string.morning_temperature), x.getTemperature().getMorningTemperature());
            cv.put(getString(context, R.string.day_temperature), x.getTemperature().getDayTemperature());
            cv.put(getString(context, R.string.evening_temperature), x.getTemperature().getEveningTemperature());
            cv.put(getString(context, R.string.night_temperature), x.getTemperature().getNightTemperature());
            cv.put(getString(context, R.string.icon), x.getIcon());
            cv.put(getString(context, R.string.description), x.getDescription());
            cv.put(getString(context, R.string.wind_speed), x.getDetail().getWindSpeed());
            cv.put(getString(context, R.string.wind_direction), x.getDetail().getWindDirection());
            cv.put(getString(context, R.string.pressure), x.getDetail().getPressure());
            cv.put(getString(context, R.string.humidity), x.getDetail().getHumidity());

            dataBase.insert(getString(context, R.string.db_name), null, cv);
        }
    }

    private String getString(Context context, int id) {
        return context.getResources().getString(id);
    }

}
