package com.vladbytsyuk.weatherforecastproject.model;

/**
 * Created by VladBytsyuk on 23.11.2015.
 */
public class WeatherForecast {
    private Temperature temperature;
    private String day;
    private String icon;
    private String description;
    private DetailWeatherForecast detail;

    public WeatherForecast() {
        this.temperature = new Temperature();
        this.day = null;
        this.icon = null;
        this.description = null;
        this.detail = new DetailWeatherForecast();
    }

    public WeatherForecast(Temperature temperature, String day, String icon,
                           String description, DetailWeatherForecast detail) {
        this.temperature = temperature;
        this.day = day;
        this.icon = icon;
        this.description = description;
        this.detail = detail;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
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
