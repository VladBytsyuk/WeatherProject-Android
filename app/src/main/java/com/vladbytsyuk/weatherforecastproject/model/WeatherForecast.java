package com.vladbytsyuk.weatherforecastproject.model;

/**
 * Created by VladBytsyuk on 23.11.2015.
 */
public class WeatherForecast {
    private String day;
    private Temperature temperature;
    private String icon;
    private String description;
    private DetailWeatherForecast detail;

    public WeatherForecast() {
        this.day = null;
        this.temperature = new Temperature();
        this.icon = null;
        this.description = null;
        this.detail = new DetailWeatherForecast();
    }

    public WeatherForecast( String day,Temperature temperature, String icon,
                           String description, DetailWeatherForecast detail) {
        this.day = day;
        this.temperature = temperature;
        this.icon = icon;
        this.description = description;
        this.detail = detail;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DetailWeatherForecast getDetail() {
        return detail;
    }

    public void setDetail(DetailWeatherForecast detail) {
        this.detail = detail;
    }
}
