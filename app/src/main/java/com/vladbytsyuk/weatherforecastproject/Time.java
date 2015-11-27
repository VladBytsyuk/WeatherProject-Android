package com.vladbytsyuk.weatherforecastproject;

/**
 * Created by VladBytsyuk on 23.11.2015.
 */
public class Time {
    private String date;
    private String weekDay;

    public Time(String date, String weekDay) {
        this.date = date;
        this.weekDay = weekDay;
    }

    public String getDate() {
        return date;
    }

    public String getWeekDay() {
        return weekDay;
    }
}
