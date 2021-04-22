package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;

public class App extends Application {

    private static Context sContext;

    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }
    public static Context getContext() {
        return sContext;
    }
}