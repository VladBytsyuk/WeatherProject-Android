package com.vladbytsyuk.weatherforecastproject;

/**
 * Created by VladBytsyuk on 23.11.2015.
 */
public class DetailWeatherForecast {
    private Float windSpeed;
    private Float windDirection;
    private Float pressure;
    private Float humidity;

    public DetailWeatherForecast(Float windSpeed, Float windDirection, Float pressure, Float humidity) {
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public Float getWindSpeed() {
        return windSpeed;
    }

    public Float getWindDirection() {
        return windDirection;
    }

    public Float getPressure() {
        return pressure;
    }

    public Float getHumidity() {
        return humidity;
    }
}
