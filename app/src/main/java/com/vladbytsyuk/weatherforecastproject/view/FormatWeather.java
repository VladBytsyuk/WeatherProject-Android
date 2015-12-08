package com.vladbytsyuk.weatherforecastproject.view;

import android.content.Context;
import android.content.SharedPreferences;

import com.vladbytsyuk.weatherforecastproject.R;
import com.vladbytsyuk.weatherforecastproject.model.WeatherForecast;

/**
 * Created by VladBytsyuk on 08.12.2015.
 */
public class FormatWeather {
    private static Context context;
    public static void setContext(Context newContext) {
       context = newContext;
    }

    public static int getWeatherIcon(WeatherForecast weatherForecast) {
        String icon = weatherForecast.getIcon();
        switch (icon.substring(0, 2)) {
            case "01" : return R.drawable.sun;
            case "02" : return R.drawable.sun_cloud;
            case "03" : return R.drawable.cloud;
            case "04" : return R.drawable.light_rain;
            case "09" : return R.drawable.rain;
            case "10" : return R.drawable.hard_rain;
            case "11" : return R.drawable.light;
            case "13" : return R.drawable.snow;
            case "50" : return R.drawable.fog;
            default   : return R.drawable.sun_cloud;
        }
    }

    public static int getDayTimeIcon(int position) {
        switch (position) {
            case 0  : return R.drawable.morning;
            case 1  : return R.drawable.day;
            case 2  : return R.drawable.evening;
            case 3  : return R.drawable.night;
            default : return R.drawable.day;
        }
    }

    public static String temperatureToString(Integer temp) {
        String degrees = null;
        SharedPreferences settings = context
                .getSharedPreferences(context.getString(R.string.shared_preferences_file_name), Context.MODE_PRIVATE);
        String metric = settings.getString(context.getString(R.string.metric), "");
        String celsiumMetric = context.getString(R.string.celsium);
        String farenheitMetric = context.getString(R.string.farenheit);
        if (metric.equals(celsiumMetric)) {
            degrees = "\u2103";
        } else if (metric.equals(farenheitMetric)) {
            degrees = "\u2109";
        } else {
            degrees = "\u2100";
        }
        return (temp >= 0 ? "+" + temp.toString() + degrees : temp.toString() + degrees);
    }

    public static String getDate(String date) {
        StringBuilder builder = new StringBuilder();
        if (date.charAt(0) != '0')
            builder.append(date.charAt(0));
        builder.append(date.charAt(1));
        builder.append(" " + getMonth(date.substring(3, 5)));
        return builder.toString();
    }

    public static String getMonth(String month) {
        String[] months = context.getResources().getStringArray(R.array.months);
        switch (month) {
            case "01" : return months[0];
            case "02" : return months[1];
            case "03" : return months[2];
            case "04" : return months[3];
            case "05" : return months[4];
            case "06" : return months[5];
            case "07" : return months[6];
            case "08" : return months[7];
            case "09" : return months[8];
            case "10" : return months[9];
            case "11" : return months[10];
            case "12" : return months[11];
            default   : return "";
        }
    }

    public static String getWindSpeed(WeatherForecast weatherForecast) {
        Integer windSpeed = weatherForecast.getDetail().getWindSpeed();
        String units = null;
        SharedPreferences settings = context
                .getSharedPreferences(context.getString(R.string.shared_preferences_file_name), Context.MODE_PRIVATE);
        String metric = settings.getString(context.getString(R.string.metric), "");
        String celsiumMetric = context.getString(R.string.celsium);
        String farenheitMetric = context.getString(R.string.farenheit);
        if (metric.equals(celsiumMetric)) {
            units = "m/s";
        } else if (metric.equals(farenheitMetric)) {
            units = " MPH";
        } else {
            units = "none";
        }
        return windSpeed.toString() + " " + units;
    }

    public static String getPressure(WeatherForecast weatherForecast) {
        Integer pressure = weatherForecast.getDetail().getPressure();
        return pressure.toString() + " hPa";
    }

    public static String getHumidity(WeatherForecast weatherForecast) {
        Integer humidity = weatherForecast.getDetail().getHumidity();
        return humidity.toString() + " %";
    }

    private String getSharedPreferenceString(int key) {
        SharedPreferences settings = context
                .getSharedPreferences(context.getString(R.string.shared_preferences_file_name), Context.MODE_PRIVATE);
        return settings.getString(context.getString(key), null);
    }
}
