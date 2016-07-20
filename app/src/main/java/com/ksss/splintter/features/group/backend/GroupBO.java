package com.ksss.splintter.features.group.backend;

import android.support.annotation.NonNull;

import com.ksss.splintter.features.group.backend.exception.EmptyNameException;
import com.ksss.splintter.features.group.backend.exception.NameTooShortException;
import com.ksss.splintter.features.group.domain.Expense;
import com.ksss.splintter.features.group.domain.Group;

import java.util.List;

/**
 * Created by Nahuel Barrios on 7/19/16.
 */
public interface GroupBO {

    void create(@NonNull String name) throws EmptyNameException, NameTooShortException;

    List<Expense> getSortedExpenses(@NonNull Group group);
}
