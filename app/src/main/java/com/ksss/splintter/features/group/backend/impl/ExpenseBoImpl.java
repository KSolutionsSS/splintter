package com.ksss.splintter.features.group.backend.impl;

import com.ksss.splintter.features.group.backend.ExpenseBo;
import com.ksss.splintter.features.group.domain.Expense;
import com.ksss.splintter.features.group.domain.Group;

/**
 * Created by Nahuel Barrios on 10/2/16.
 */
public class ExpenseBoImpl implements ExpenseBo {

    @Override
    public Expense create(final Float amount, final String description, final Group group) {
        return new Expense(amount, description, group);
    }
}
