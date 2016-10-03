package com.ksss.splintter.data.business;

import com.ksss.splintter.data.model.Expense;
import com.ksss.splintter.data.model.Group;
import com.ksss.splintter.data.model.Person;

/**
 * Created by Nahuel Barrios on 10/2/16.
 */
public interface ExpenseBo {

    Expense create(final Float amount, final String description, final Group group, final Person person);
}
