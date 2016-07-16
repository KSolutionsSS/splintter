package com.ksss.splintter;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Nahuel Barrios on 7/16/16.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.d("Creating MAIN application...");
    }
}
