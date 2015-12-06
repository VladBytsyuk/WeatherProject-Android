package com.vladbytsyuk.weatherforecastproject.model;

/**
 * Created by BVS on 06.12.2015.
 */
public class DayTimeWeatherForecast {
    String dayTime;
    Integer tempeature;

    public DayTimeWeatherForecast(String dayTime, Integer tempeature) {
        this.dayTime = dayTime;
        this.tempeature = tempeature;
    }

    public String getDayTime() {
        return dayTime;
    }

    public void setDayTime(String dayTime) {
        this.dayTime = dayTime;
    }

    public Integer getTempeature() {
        return tempeature;
    }

    public void setTempeature(Integer tempeature) {
        this.tempeature = tempeature;
    }
}
