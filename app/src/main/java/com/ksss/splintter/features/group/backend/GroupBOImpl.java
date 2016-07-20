package com.ksss.splintter.features.group.backend;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.ksss.splintter.features.group.backend.exception.EmptyNameException;
import com.ksss.splintter.features.group.backend.exception.NameTooShortException;
import com.ksss.splintter.features.group.domain.Expense;
import com.ksss.splintter.features.group.domain.Group;

import java.util.List;

import timber.log.Timber;

/**
 * Created by Nahuel Barrios on 7/19/16.
 */
public class GroupBOImpl implements GroupBO {

    @Override
    public void create(@NonNull String name) throws EmptyNameException, NameTooShortException {
        if (TextUtils.isEmpty(name)) {
            throw new EmptyNameException();
        }

        if (name.length() < 2) {
            throw new NameTooShortException();
        }

        // TODO: 7/19/16 Persist it!
        Timber.e("Must persist a new group!");
    }

    @Override
    public List<Expense> getSortedExpenses(@NonNull Group group) {
        return null;
    }

}
