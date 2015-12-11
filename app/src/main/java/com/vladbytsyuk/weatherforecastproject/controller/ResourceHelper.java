package com.vladbytsyuk.weatherforecastproject.controller;

import android.content.Context;

/**
 * Created by VladBytsyuk on 12.12.2015.
 */
public class ResourceHelper {
    private static Context context;

    public static void setContext(Context newContext){
        context = newContext;
    }

    public static String getString(int id) {
        return context.getResources().getString(id);
    }

    public static String[] getStringArray(int id) {
        return context.getResources().getStringArray(id);
    }
}
