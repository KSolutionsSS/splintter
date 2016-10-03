package com.ksss.splintter.features.group.backend.impl;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.ksss.splintter.features.group.backend.GroupBo;
import com.ksss.splintter.features.group.backend.exception.EmptyNameException;
import com.ksss.splintter.features.group.backend.exception.NameTooShortException;
import com.ksss.splintter.features.group.domain.Group;
import hugo.weaving.DebugLog;

/**
 * Created by Nahuel Barrios on 7/19/16.
 */
public class GroupBoImpl implements GroupBo {

    @DebugLog
    @Override
    public Group create(@NonNull final String name) throws EmptyNameException, NameTooShortException {
        if (TextUtils.isEmpty(name)) {
            throw new EmptyNameException();
        }

        if (name.length() < 2) {
            throw new NameTooShortException();
        }

        return new Group(name);
    }

}
