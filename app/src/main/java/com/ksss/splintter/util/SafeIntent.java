package com.ksss.splintter.util;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Created by Nahuel Barrios on 7/16/16.
 */
public class SafeIntent extends Intent {

    public SafeIntent(@NonNull final Uri uri) {
        setPackage(getPackage());
        setData(uri);
    }
}
