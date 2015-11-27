package com.vladbytsyuk.weatherforecastproject;

/**
 * Created by VladBytsyuk on 23.11.2015.
 */
public class WeatherForecast {
    private Temperature temperature;
    private Time time;
    private String descriptionIcon;
    private DetailWeatherForecast detail;

    public WeatherForecast(Temperature temperature, Time  time, String descriptionIcon, DetailWeatherForecast detail) {
        this.temperature = temperature;
        this.time = time;
        this.descriptionIcon = descriptionIcon;
        this.detail = detail;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public Time getTime() {
        return time;
    }

    public String getDescriptionIcon() {
        return descriptionIcon;
    }

    public DetailWeatherForecast getDetail() {
        return detail;
    }
}
