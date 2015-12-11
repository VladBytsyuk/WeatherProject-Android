package com.vladbytsyuk.weatherforecastproject.controller.async_task;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.vladbytsyuk.weatherforecastproject.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by BVS on 28.11.2015.
 */
public class JSONDownloader {
    public static String downloadJSON(String city, String metric, String lang) {
        String result = null;
        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?" +
                                "q=" + URLEncoder.encode(city, "UTF-8") +
                                "&units=" + metric +
                                "&lang=" + lang +
                                "&type=" + "accurate" +
                                "&cnt=" + "14" +
                                "&appid=" + "2de143494c0b295cca9337e1e96b00e0");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(10000 /* milliseconds */);
            connection.connect();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
            reader.close();
            connection.disconnect();
            result = builder.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
