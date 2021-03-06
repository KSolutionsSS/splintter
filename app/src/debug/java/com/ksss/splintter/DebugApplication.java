package com.ksss.splintter;

import android.os.Handler;
import android.os.StrictMode;

import com.facebook.stetho.Stetho;
import com.nshmura.strictmodenotifier.StrictModeNotifier;
import com.squareup.leakcanary.LeakCanary;

import hugo.weaving.DebugLog;
import timber.log.Timber;

/**
 * Created by Nahuel Barrios on 7/16/16.
 */
public class DebugApplication extends MainApplication {

    @DebugLog
    @Override
    public void onCreate() {
        Timber.plant(new Timber.DebugTree());

        super.onCreate();

        Timber.d("Creating DEBUG application...");

        Stetho.initializeWithDefaults(this);
        LeakCanary.install(this);
        setupStrictModeNotifier();
    }

    /**
     * More info at <a href="https://github.com/nshmura/strictmode-notifier">github.com/nshmura/strictmode-notifier</a>.
     */
    private void setupStrictModeNotifier() {
        StrictModeNotifier.install(this);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder()
                        .detectAll()
                        .permitDiskReads()
                        .permitDiskWrites()
                        .penaltyLog() // Required for StrictModeNotifier!
                        .build();
                StrictMode.setThreadPolicy(threadPolicy);

                StrictMode.VmPolicy vmPolicy = new StrictMode.VmPolicy.Builder()
                        .detectAll()
                        .penaltyLog() // Required for StrictModeNotifier!
                        .build();
                StrictMode.setVmPolicy(vmPolicy);
            }
        });
    }
}
