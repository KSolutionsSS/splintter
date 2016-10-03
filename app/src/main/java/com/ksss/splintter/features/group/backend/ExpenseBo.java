package com.ksss.splintter.features.group.backend;

import com.ksss.splintter.features.group.domain.Expense;
import com.ksss.splintter.features.group.domain.Group;
import com.ksss.splintter.features.group.domain.Person;

/**
 * Created by Nahuel Barrios on 10/2/16.
 */
public interface ExpenseBo {

    Expense create(final Float amount, final String description, final Group group, final Person person);
}
