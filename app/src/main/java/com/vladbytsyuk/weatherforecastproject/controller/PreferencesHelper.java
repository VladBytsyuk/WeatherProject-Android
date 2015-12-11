package com.vladbytsyuk.weatherforecastproject.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.vladbytsyuk.weatherforecastproject.R;

/**
 * Created by VladBytsyuk on 11.12.2015.
 */
public class PreferencesHelper {
    private static Context context;

    public static void setContext(Context newContext){
           context = newContext;
    }

    public static String getPreference(int preference) {
        SharedPreferences settings = context
                .getSharedPreferences(context
                        .getString(R.string.shared_preferences_file_name), Context.MODE_PRIVATE);
        return settings.getString(context.getString(preference), null);
    }

    public static String getPreference(int preference, String value) {
        SharedPreferences settings = context
                .getSharedPreferences(context
                        .getString(R.string.shared_preferences_file_name), Context.MODE_PRIVATE);
        return settings.getString(context.getString(preference), value);
    }

    public static void setPreference(int key, String value) {
        SharedPreferences settings = context
                .getSharedPreferences(context.getResources()
                        .getString(R.string.shared_preferences_file_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(context.getResources().getString(key), value).apply();
    }
}
