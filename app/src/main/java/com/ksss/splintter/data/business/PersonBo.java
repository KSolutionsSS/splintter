package com.ksss.splintter.data.business;

import android.support.annotation.NonNull;
import com.ksss.splintter.data.business.exception.EmptyNameException;
import com.ksss.splintter.data.business.exception.NameTooShortException;
import com.ksss.splintter.data.model.Person;

/**
 * Created by Nahuel Barrios on 7/19/16.
 */
public interface PersonBo {

    @NonNull
    Person create(@NonNull String name) throws EmptyNameException, NameTooShortException;
}
