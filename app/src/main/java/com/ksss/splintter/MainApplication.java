package com.ksss.splintter;

import android.app.Application;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.MaterialModule;

import hugo.weaving.DebugLog;
import timber.log.Timber;

/**
 * Created by Nahuel Barrios on 7/16/16.
 */
public class MainApplication extends Application {

    @DebugLog
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.d("Creating MAIN application...");

        Iconify.with(new MaterialModule());
    }
}
