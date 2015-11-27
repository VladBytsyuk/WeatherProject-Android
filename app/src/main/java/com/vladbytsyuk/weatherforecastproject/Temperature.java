package com.vladbytsyuk.weatherforecastproject;

/**
 * Created by VladBytsyuk on 23.11.2015.
 */
public class Temperature {
    private Integer morningTemperature;
    private Integer dayTemperature;
    private Integer eveningTemperature;
    private Integer nightTemperature;

    public Temperature(Float morningTemperature, Float dayTemperature, Float eveningTemperature, Float nightTemperature) {
        this.morningTemperature = Math.round(morningTemperature);
        this.dayTemperature = Math.round(dayTemperature);
        this.eveningTemperature = Math.round(eveningTemperature);
        this.nightTemperature = Math.round(nightTemperature);
    }

    public Integer getMorningTemperature() {
        return morningTemperature;
    }

    public Integer getDayTemperature() {
        return dayTemperature;
    }

    public Integer getEveningTemperature() {
        return eveningTemperature;
    }

    public Integer getNightTemperature() {
        return nightTemperature;
    }
}
