package com.ksss.splintter.features.group.domain;

import android.support.annotation.NonNull;

/**
 * Created by Nahuel Barrios on 7/17/16.
 */
public class Group {
    private String name;

    public Group(@NonNull String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
