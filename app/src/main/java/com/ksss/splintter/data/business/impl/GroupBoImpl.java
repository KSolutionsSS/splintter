package com.ksss.splintter.data.business.impl;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.ksss.splintter.data.business.GroupBo;
import com.ksss.splintter.data.business.exception.EmptyNameException;
import com.ksss.splintter.data.business.exception.NameTooShortException;
import com.ksss.splintter.data.model.Group;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * Created by Nahuel Barrios on 7/19/16.
 */
public class GroupBoImpl implements GroupBo {

    @Override
    @SuppressFBWarnings(
        value = "UCC_UNRELATED_COLLECTION_CONTENTS"
        , justification = "I think this is a Findbugs issue with Realm"
    )
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
