package com.vladbytsyuk.weatherforecastproject.model;

/**
 * Created by VladBytsyuk on 23.11.2015.
 */
public class DetailWeatherForecast {
    private Integer windSpeed;
    private Integer windDirection;
    private Integer pressure;
    private Integer humidity;

    public DetailWeatherForecast() {
        this.windSpeed = 0;
        this.windDirection = 0;
        this.pressure = 0;
        this.humidity = 0;
    }

    public DetailWeatherForecast(Integer windSpeed, Integer windDirection, Integer pressure,
                                 Integer humidity, String description) {
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public Integer getWindSpeed() {
        return windSpeed;
    }

    public Integer getWindDirection() {
        return windDirection;
    }

    public Integer getPressure() {
        return pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setWindSpeed(Integer windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDirection(Integer windDirection) {
        this.windDirection = windDirection;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

}
