package com.vladbytsyuk.weatherforecastproject.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by VladBytsyuk on 11.12.2015.
 */
public final class NetworkHelper {
    private static Context context;

    public static void setContext(Context newContext){
        context = newContext;
    }

    public static Boolean isNetworkAvailable() {
        final ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
