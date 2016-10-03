package com.ksss.splintter.data.business.impl;

import com.ksss.splintter.data.business.ExpenseBo;
import com.ksss.splintter.data.model.Expense;
import com.ksss.splintter.data.model.Group;
import com.ksss.splintter.data.model.Person;

/**
 * Created by Nahuel Barrios on 10/2/16.
 */
public class ExpenseBoImpl implements ExpenseBo {

    @Override
    public Expense create(final Float amount, final String description, final Group group, final Person person) {
        final Expense expense = new Expense(amount, description, group);
        expense.setPerson(person);
        return expense;
    }
}
