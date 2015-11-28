package com.vladbytsyuk.weatherforecastproject.controller;

import com.vladbytsyuk.weatherforecastproject.model.DetailWeatherForecast;
import com.vladbytsyuk.weatherforecastproject.model.Temperature;
import com.vladbytsyuk.weatherforecastproject.model.WeatherForecast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by BVS on 28.11.2015.
 */
public class JSONParser {
    public static ArrayList<WeatherForecast> parseJSON(String jsonFile) {
        ArrayList<WeatherForecast> weatherForecasts = new ArrayList<>();

        try {
            JSONObject file = new JSONObject(jsonFile);
            JSONArray list = file.getJSONArray("list");

            for (int it = 0; it < 14; ++it) {
                WeatherForecast weatherForecast = new WeatherForecast();

                JSONObject listElem = list.getJSONObject(it);
                Integer dt =listElem.getInt("dt");
                //TODO: parse this number to real date
                weatherForecast.setDay(dt.toString());

                JSONObject temp = listElem.getJSONObject("temp");
                Temperature temperature = new Temperature();
                temperature.setMorningTemperature(Math.round(temp.getLong("morn")));
                temperature.setDayTemperature(Math.round(temp.getLong("day")));
                temperature.setEveningTemperature(Math.round(temp.getLong("eve")));
                temperature.setNightTemperature(Math.round(temp.getLong("night")));
                weatherForecast.setTemperature(temperature);

                JSONArray weather = listElem.getJSONArray("weather");
                JSONObject weatherElem = weather.getJSONObject(0);
                weatherForecast.setIcon(weatherElem.getString("icon"));
                weatherForecast.setDescription(weatherElem.getString("description"));

                DetailWeatherForecast detail = new DetailWeatherForecast();
                detail.setWindSpeed(Math.round(listElem.getLong("speed")));
                detail.setWindDirection(Math.round(listElem.getLong("deg")));
                detail.setPressure(Math.round(listElem.getLong("pressure")));
                detail.setHumidity(Math.round(listElem.getLong("humidity")));
                weatherForecast.setDetail(detail);

                weatherForecasts.add(weatherForecast);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return weatherForecasts;
    }
}
