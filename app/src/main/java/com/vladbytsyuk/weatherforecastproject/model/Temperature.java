package com.vladbytsyuk.weatherforecastproject.model;

/**
 * Created by VladBytsyuk on 23.11.2015.
 */
public class Temperature {
    private Integer morningTemperature;
    private Integer dayTemperature;
    private Integer eveningTemperature;
    private Integer nightTemperature;

    public Temperature() {
        this.morningTemperature = 0;
        this.dayTemperature = 0;
        this.eveningTemperature = 0;
        this.nightTemperature = 0;
    }

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

    public void setMorningTemperature(Integer morningTemperature) {
        this.morningTemperature = morningTemperature;
    }

    public void setDayTemperature(Integer dayTemperature) {
        this.dayTemperature = dayTemperature;
    }

    public void setEveningTemperature(Integer eveningTemperature) {
        this.eveningTemperature = eveningTemperature;
    }

    public void setNightTemperature(Integer nightTemperature) {
        this.nightTemperature = nightTemperature;
    }
}
