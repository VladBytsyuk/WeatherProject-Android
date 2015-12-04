package com.vladbytsyuk.weatherforecastproject.controller.async_task;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by VladBytsyuk on 29.11.2015.
 */
public class ContextAndDB {
    public Context context;
    public SQLiteDatabase database;

    public ContextAndDB(Context context, SQLiteDatabase database) {
        this.context = context;
        this.database = database;
    }
}