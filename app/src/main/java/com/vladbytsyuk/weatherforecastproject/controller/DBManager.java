package com.vladbytsyuk.weatherforecastproject.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vladbytsyuk.weatherforecastproject.R;

/**
 * Created by BVS on 28.11.2015.
 */
public class DBManager extends SQLiteOpenHelper {
    private String name;
    private String day;
    private String morningTemperature;
    private String dayTemperature;
    private String eveningTemperature;
    private String nightTemperature;
    private String icon;
    private String description;
    private String windSpeed;
    private String windDirection;
    private String pressure;
    private String humidity;


    public DBManager(Context context) {
        super(context, context.getResources().getString(R.string.db_name), null, 1);

        name = getString(context, R.string.db_name);
        setColumnTitles(context);
    }

    private void setColumnTitles(Context context) {
        day = getString(context, R.string.day);
        morningTemperature = getString(context, R.string.morning_temperature);
        dayTemperature = getString(context, R.string.day_temperature);
        eveningTemperature = getString(context, R.string.evening_temperature);
        nightTemperature = getString(context, R.string.night_temperature);
        icon = getString(context, R.string.icon);
        description = getString(context, R.string.description);
        windSpeed = getString(context, R.string.wind_speed);
        windDirection = getString(context, R.string.wind_direction);
        pressure = getString(context, R.string.pressure);
        humidity = getString(context, R.string.humidity);
    }

    private String getString(Context context, int id) {
        return context.getResources().getString(id);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + name +
                      "(id integer primary keyautoincrement, " +
                      day + " text" +
                      morningTemperature + " integer" +
                      dayTemperature + " integer" +
                      eveningTemperature + " integer" +
                      nightTemperature + " integer" +
                      icon + " text" +
                      description + " text" +
                      windSpeed + " integer" +
                      windDirection + " integer" +
                      pressure + " integer" +
                      humidity + " integer" +
                      ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}
