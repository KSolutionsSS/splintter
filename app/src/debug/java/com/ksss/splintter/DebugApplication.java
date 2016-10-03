package com.ksss.splintter;

import android.net.Uri;
import android.os.Handler;
import android.os.StrictMode;
import com.facebook.stetho.Stetho;
import com.nshmura.strictmodenotifier.StrictModeNotifier;
import com.squareup.leakcanary.LeakCanary;
import hugo.weaving.DebugLog;
import io.realm.Realm;
import timber.log.Timber;

import static com.ksss.splintter.DummyDatabaseHelper.Status.POPULATED;

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

        Timber.d("Want to browse your Realm Database?");
        Timber.d("Browse Realm database using: https://github.com/realm/realm-browser-osx");

        final Uri databaseLocation = Uri.parse(Realm.getDefaultInstance().getPath());
        Timber.d(
            "> adb pull %s; open %s"
            , databaseLocation.toString()
            , databaseLocation.getPathSegments().get(databaseLocation.getPathSegments().size() - 1)
        );

        // TODO: 10/2/16 From now on, run initializations on a background thread.
        Stetho.initializeWithDefaults(this);
        LeakCanary.install(this);
        setupStrictModeNotifier();

        final DummyDatabaseHelper.Status databaseStatus = POPULATED;
        switch (databaseStatus) {
            case CLEAN:
                DummyDatabaseHelper.clean();
                break;
            case POPULATED:
                DummyDatabaseHelper.clean();
                DummyDatabaseHelper.populate();
                DummyDatabaseHelper.check();
                break;
            case PREVIOUS_SESSION:
            default:
                break;
        }

        Timber.i("Application started with database status: %s", databaseStatus);
    }

    /**
     * More info at <a href="https://github.com/nshmura/strictmode-notifier">github.com/nshmura/strictmode-notifier</a>.
     */
    private void setupStrictModeNotifier() {
        StrictModeNotifier.install(this);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                final StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog() // Required for StrictModeNotifier!
                    .build();
                StrictMode.setThreadPolicy(threadPolicy);

                final StrictMode.VmPolicy vmPolicy = new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog() // Required for StrictModeNotifier!
                    .build();
                StrictMode.setVmPolicy(vmPolicy);
            }
        });
    }
}
