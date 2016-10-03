package com.ksss.splintter.features.group.domain;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import java.util.List;

/**
 * Created by Nahuel Barrios on 7/17/16.
 */
public class Person extends RealmObject {

    @PrimaryKey
    private String name;

    private RealmList<Expense> expenses;

    /**
     * Default constructor is required by Realm.
     */
    public Person() {
        // Do nothing.
    }

    public Person(final String name) {
        this.name = name;
        expenses = new RealmList<>();
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(final List<Expense> expenses) {
        this.expenses = (RealmList<Expense>) expenses;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            '}';
    }
}
