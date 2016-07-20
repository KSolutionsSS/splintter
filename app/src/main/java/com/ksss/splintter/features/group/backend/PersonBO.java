package com.ksss.splintter.features.group.backend;

import android.support.annotation.NonNull;

import com.ksss.splintter.features.group.backend.exception.EmptyNameException;
import com.ksss.splintter.features.group.backend.exception.NameTooShortException;

/**
 * Created by Nahuel Barrios on 7/19/16.
 */
public interface PersonBO {

    void create(@NonNull String name) throws EmptyNameException, NameTooShortException;
}
