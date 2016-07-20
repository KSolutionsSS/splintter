package com.ksss.splintter.features.group.backend;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.ksss.splintter.features.group.backend.exception.EmptyNameException;
import com.ksss.splintter.features.group.backend.exception.NameTooShortException;

import timber.log.Timber;

/**
 * Created by Nahuel Barrios on 7/19/16.
 */

public class PersonBOImpl implements PersonBO {

    @Override
    public void create(@NonNull String name) throws EmptyNameException, NameTooShortException {
        if (TextUtils.isEmpty(name)) {
            throw new EmptyNameException();
        }

        if (name.length() < 2) {
            throw new NameTooShortException();
        }

        // TODO: 7/19/16 Persist it!
        Timber.e("Must persist a new person!");
    }
}
