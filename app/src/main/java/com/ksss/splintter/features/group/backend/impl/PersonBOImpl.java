package com.ksss.splintter.features.group.backend.impl;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.ksss.splintter.features.group.backend.PersonBo;
import com.ksss.splintter.features.group.backend.exception.EmptyNameException;
import com.ksss.splintter.features.group.backend.exception.NameTooShortException;
import com.ksss.splintter.features.group.domain.Person;
import hugo.weaving.DebugLog;

/**
 * Created by Nahuel Barrios on 7/19/16.
 */

public class PersonBoImpl implements PersonBo {

    @DebugLog
    @NonNull
    @Override
    public Person create(@NonNull final String name) throws EmptyNameException, NameTooShortException {
        if (TextUtils.isEmpty(name)) {
            throw new EmptyNameException();
        }

        if (name.length() < 2) {
            throw new NameTooShortException();
        }

        return new Person(name);
    }
}
