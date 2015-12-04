package com.vladbytsyuk.weatherforecastproject.controller.async_task;

import com.vladbytsyuk.weatherforecastproject.model.DetailWeatherForecast;
import com.vladbytsyuk.weatherforecastproject.model.Temperature;
import com.vladbytsyuk.weatherforecastproject.model.WeatherForecast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

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
                Long dt = listElem.getLong("dt");
                Date date = new Date(dt * 1000L);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyy");
                String formattedDate = simpleDateFormat.format(date);
                weatherForecast.setDay(formattedDate);

                JSONObject temp = listElem.getJSONObject("temp");
                Temperature temperature = new Temperature();
                temperature.setMorningTemperature(Math.round(temp.getLong("morn")));
                temperature.setDayTemperature(Math.round(temp.getLong("day")));
                temperature.setEveningTemperature(Math.round(temp.getLong("eve")));
                temperature.setNightTemperature(Math.round(temp.getLong("night")));
                temperature.setMinTemperature(Math.round(temp.getLong("min")));
                temperature.setMaxTemperature(Math.round(temp.getLong("max")));
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
