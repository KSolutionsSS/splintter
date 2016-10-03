package com.ksss.splintter.features.group.backend;

import android.support.annotation.NonNull;
import com.ksss.splintter.features.group.backend.exception.EmptyNameException;
import com.ksss.splintter.features.group.backend.exception.NameTooShortException;
import com.ksss.splintter.features.group.domain.Group;

/**
 * Created by Nahuel Barrios on 7/19/16.
 */
public interface GroupBo {

    Group create(@NonNull String name) throws EmptyNameException, NameTooShortException;

}
