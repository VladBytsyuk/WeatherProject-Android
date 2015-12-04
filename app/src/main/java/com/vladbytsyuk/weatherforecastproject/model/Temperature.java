package com.vladbytsyuk.weatherforecastproject.model;

/**
 * Created by VladBytsyuk on 23.11.2015.
 */
public class Temperature {
    private Integer morningTemperature;
    private Integer dayTemperature;
    private Integer eveningTemperature;
    private Integer nightTemperature;
    private Integer minTemperature;
    private Integer maxTemperature;


    public Temperature() {
        this.morningTemperature = 0;
        this.dayTemperature = 0;
        this.eveningTemperature = 0;
        this.nightTemperature = 0;
        this.minTemperature = 0;
        this.maxTemperature = 0;
    }

    public Temperature(Integer morningTemperature, Integer dayTemperature, Integer eveningTemperature, Integer nightTemperature, Integer minTemperature, Integer maxTemperature) {
        this.morningTemperature = Math.round(morningTemperature);
        this.dayTemperature = Math.round(dayTemperature);
        this.eveningTemperature = Math.round(eveningTemperature);
        this.nightTemperature = Math.round(nightTemperature);
        this.minTemperature = Math.round(minTemperature);
        this.maxTemperature = Math.round(maxTemperature);
    }

    public Integer getMorningTemperature() {
        return morningTemperature;
    }

    public void setMorningTemperature(Integer morningTemperature) {
        this.morningTemperature = morningTemperature;
    }

    public Integer getDayTemperature() {
        return dayTemperature;
    }

    public void setDayTemperature(Integer dayTemperature) {
        this.dayTemperature = dayTemperature;
    }

    public Integer getEveningTemperature() {
        return eveningTemperature;
    }

    public void setEveningTemperature(Integer eveningTemperature) {
        this.eveningTemperature = eveningTemperature;
    }

    public Integer getNightTemperature() {
        return nightTemperature;
    }

    public void setNightTemperature(Integer nightTemperature) {
        this.nightTemperature = nightTemperature;
    }

    public Integer getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Integer minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Integer getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Integer maxTemperature) {
        this.maxTemperature = maxTemperature;
    }
}
