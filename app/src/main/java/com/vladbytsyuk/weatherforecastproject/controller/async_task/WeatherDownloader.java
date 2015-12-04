package com.vladbytsyuk.weatherforecastproject.controller.async_task;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.vladbytsyuk.weatherforecastproject.R;
import com.vladbytsyuk.weatherforecastproject.model.WeatherForecast;

import java.util.ArrayList;

/**
 * Created by VladBytsyuk on 29.11.2015.
 */
public class WeatherDownloader extends AsyncTask<ContextAndDB, Void, Void> {
    private  Context context;
    private  SQLiteDatabase database;

    @Override
    protected Void doInBackground(ContextAndDB... params) {
        context = params[0].context;
        database = params[0].database;
        String city = getPreference(R.string.city);
        String metric = getPreference(R.string.metric);
        String jsonFile = JSONDownloader.downloadJSON(city, metric);
        ArrayList<WeatherForecast> weatherForecasts = JSONParser.parseJSON(jsonFile);
        fillDataBase(weatherForecasts);
        return null;
    }

    private String getPreference(int preference) {
        SharedPreferences settings = context.getSharedPreferences(context.getString(R.string.shared_preferences_file_name), Context.MODE_PRIVATE);
        return settings.getString(context.getString(preference), null);
    }

    private void fillDataBase(ArrayList<WeatherForecast> weatherForecasts) {
        for (WeatherForecast x : weatherForecasts) {
            ContentValues cv = new ContentValues();

            cv.put(getString(R.string.day), x.getDay());
            cv.put(getString(R.string.morning_temperature), x.getTemperature().getMorningTemperature());
            cv.put(getString(R.string.day_temperature), x.getTemperature().getDayTemperature());
            cv.put(getString(R.string.evening_temperature), x.getTemperature().getEveningTemperature());
            cv.put(getString(R.string.night_temperature), x.getTemperature().getNightTemperature());
            cv.put(getString(R.string.icon), x.getIcon());
            cv.put(getString(R.string.description), x.getDescription());
            cv.put(getString(R.string.wind_speed), x.getDetail().getWindSpeed());
            cv.put(getString(R.string.wind_direction), x.getDetail().getWindDirection());
            cv.put(getString(R.string.pressure), x.getDetail().getPressure());
            cv.put(getString(R.string.humidity), x.getDetail().getHumidity());

            database.insert(getString(R.string.db_name), null, cv);
        }
    }

    private String getString(int id) {
        return context.getResources().getString(id);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        database.close();
        super.onPostExecute(aVoid);
    }
}
