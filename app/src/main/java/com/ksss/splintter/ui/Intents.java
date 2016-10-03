package com.ksss.splintter.ui;

import android.net.Uri;
import android.support.annotation.NonNull;
import com.ksss.splintter.util.SafeIntent;

/**
 * Enumerates the different deep links that this application currently supports.
 * <p>
 * Created by Nahuel Barrios on 7/16/16.
 */
public enum Intents {

    GROUPS_CREATE("group/create");

    public static final String SCHEME = "splintter://";

    private final String hostAndPath;

    Intents(String hostAndPath) {
        this.hostAndPath = hostAndPath;
    }

    @NonNull
    public SafeIntent get() {
        return new SafeIntent(Uri.parse(SCHEME + hostAndPath));
    }
}
