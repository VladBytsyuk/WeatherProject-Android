package com.vladbytsyuk.weatherforecastproject.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vladbytsyuk.weatherforecastproject.R;
import com.vladbytsyuk.weatherforecastproject.controller.async_task.WeatherDownloader;
import com.vladbytsyuk.weatherforecastproject.model.DetailWeatherForecast;
import com.vladbytsyuk.weatherforecastproject.model.Temperature;
import com.vladbytsyuk.weatherforecastproject.model.WeatherForecast;

import java.util.ArrayList;

/**
 * Created by BVS on 28.11.2015.
 */
public class DBManager extends SQLiteOpenHelper {
    Context context;

    private String tableName;
    private String day;
    private String morningTemperature;
    private String dayTemperature;
    private String eveningTemperature;
    private String nightTemperature;
    private String minTemperature;
    private String maxTemperature;
    private String icon;
    private String description;
    private String windSpeed;
    private String windDirection;
    private String pressure;
    private String humidity;


    public DBManager(Context context) {
        super(context, context.getResources().getString(R.string.db_name), null, 1);
        this.context = context;
        tableName = getString(R.string.db_name);
        setColumnTitles();
    }

    private void setColumnTitles() {
        day = getString(R.string.day);
        morningTemperature = getString(R.string.morning_temperature);
        dayTemperature = getString(R.string.day_temperature);
        eveningTemperature = getString(R.string.evening_temperature);
        nightTemperature = getString(R.string.night_temperature);
        minTemperature = getString(R.string.min_temperature);
        maxTemperature = getString(R.string.max_temperature);
        icon = getString(R.string.icon);
        description = getString(R.string.description);
        windSpeed = getString(R.string.wind_speed);
        windDirection = getString(R.string.wind_direction);
        pressure = getString(R.string.pressure);
        humidity = getString(R.string.humidity);
    }

    private String getString(int id) {
        return context.getResources().getString(id);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tableName = getString(R.string.db_name);
        //setColumnTitles();
        db.execSQL("create table " + tableName +
                        "(id integer primary key autoincrement, " +
                        day + " text, " +
                        morningTemperature + " integer, " +
                        dayTemperature + " integer, " +
                        eveningTemperature + " integer, " +
                        nightTemperature + " integer, " +
                        minTemperature + " integer, " +
                        maxTemperature + " integer, " +
                        icon + " text, " +
                        description + " text, " +
                        windSpeed + " integer, " +
                        windDirection + " integer, " +
                        pressure + " integer, " +
                        humidity + " integer" +
                        ");"
        );
    }

    public void drop() {
        SQLiteDatabase database =  this.getReadableDatabase();
        database.delete(tableName, null, null);
        database.close();
    }

    public Boolean isDBEmpty() {
        Boolean result;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor c = database.rawQuery("select * from " + tableName, null);
        result = !c.moveToFirst();
        c.close();
        database.close();
        return result;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void downloadWeather() {
        ArrayList<WeatherForecast> weatherForecasts;
        WeatherDownloader weatherDownloader = new WeatherDownloader();
        weatherDownloader.execute(context);
        try {
            weatherForecasts = weatherDownloader.get();
            fillDataBase(weatherForecasts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillDataBase(ArrayList<WeatherForecast> weatherForecasts) {
        SQLiteDatabase database = this.getWritableDatabase();
        for (WeatherForecast x : weatherForecasts) {
            ContentValues cv = new ContentValues();

            cv.put(day, x.getDay());
            cv.put(morningTemperature, x.getTemperature().getMorningTemperature());
            cv.put(dayTemperature, x.getTemperature().getDayTemperature());
            cv.put(eveningTemperature, x.getTemperature().getEveningTemperature());
            cv.put(nightTemperature, x.getTemperature().getNightTemperature());
            cv.put(maxTemperature, x.getTemperature().getMaxTemperature());
            cv.put(minTemperature, x.getTemperature().getMinTemperature());
            cv.put(icon, x.getIcon());
            cv.put(description, x.getDescription());
            cv.put(windSpeed, x.getDetail().getWindSpeed());
            cv.put(windDirection, x.getDetail().getWindDirection());
            cv.put(pressure, x.getDetail().getPressure());
            cv.put(humidity, x.getDetail().getHumidity());

            database.insert(tableName, null, cv);
        }
        database.close();
    }

    public ArrayList<WeatherForecast> getWeatherForecasts() {
        ArrayList<WeatherForecast> weatherForecasts = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();

        Cursor c = database.query(tableName, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            Integer dateColumnIndex = c.getColumnIndex(day);
            Integer morningTemperatureColumnIndex = c.getColumnIndex(morningTemperature);
            Integer dayTemperatureColumnIndex = c.getColumnIndex(dayTemperature);
            Integer eveningTemperatureColumnIndex = c.getColumnIndex(eveningTemperature);
            Integer nightTemperatureColumnIndex = c.getColumnIndex(nightTemperature);
            Integer minTemperatureColumnIndex = c.getColumnIndex(minTemperature);
            Integer maxTemperatureColumnIndex = c.getColumnIndex(maxTemperature);
            Integer iconColumnIndex = c.getColumnIndex(icon);
            Integer descriptionColumnIndex = c.getColumnIndex(description);
            Integer windSpeedColumnIndex = c.getColumnIndex(windSpeed);
            Integer windDirectionColumnIndex = c.getColumnIndex(windDirection);
            Integer pressureColumnIndex = c.getColumnIndex(pressure);
            Integer humidityColumnIndex = c.getColumnIndex(humidity);
            do {
                String dateValue = c.getString(dateColumnIndex);
                Integer morningTemperatureValue = c.getInt(morningTemperatureColumnIndex);
                Integer dayTemperatureValue = c.getInt(dayTemperatureColumnIndex);
                Integer eveningTemperatureValue = c.getInt(eveningTemperatureColumnIndex);
                Integer nightTemperatureValue = c.getInt(nightTemperatureColumnIndex);
                Integer minTemperatureValue = c.getInt(minTemperatureColumnIndex);
                Integer maxTemperatureValue = c.getInt(maxTemperatureColumnIndex);
                String iconValue = c.getString(iconColumnIndex);
                String descriptionValue = c.getString(descriptionColumnIndex);
                Integer windSpeedValue = c.getInt(windSpeedColumnIndex);
                Integer windDirectionValue = c.getInt(windDirectionColumnIndex);
                Integer pressureValue = c.getInt(pressureColumnIndex);
                Integer humidityValue = c.getInt(humidityColumnIndex);
                Temperature temperatureValue = new Temperature(morningTemperatureValue,
                        dayTemperatureValue, eveningTemperatureValue, nightTemperatureValue,
                        minTemperatureValue, maxTemperatureValue);
                DetailWeatherForecast detailValue = new DetailWeatherForecast(windSpeedValue,
                        windDirectionValue, pressureValue, humidityValue);
                WeatherForecast currentWeatherForecast = new WeatherForecast(dateValue,
                        temperatureValue, iconValue, descriptionValue, detailValue);
                weatherForecasts.add(currentWeatherForecast);
            } while (c.moveToNext());
        }
        c.close();
        database.close();
        return weatherForecasts;
    }
}


