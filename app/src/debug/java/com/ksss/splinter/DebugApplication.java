package com.ksss.splinter;

import com.facebook.stetho.Stetho;

/**
 * Created by Nahuel Barrios on 7/16/16.
 */
public class DebugApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);
    }
}
