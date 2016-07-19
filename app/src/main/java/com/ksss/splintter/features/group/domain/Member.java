package com.ksss.splintter.features.group.domain;

import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Nahuel Barrios on 7/17/16.
 */
public class Member {

    private String name;
    private List<Expense> expenses;

    public Member(String name) {
        this.name = name;
        this.expenses = new ArrayList<>();

        mockExpenses();
    }

    private void mockExpenses() {
        expenses.add(new Expense(new BigDecimal(new Random().nextInt(100 - 10) + 10), "prueba"));
        expenses.add(new Expense(new BigDecimal(new Random().nextInt(5000 - 25) + 25), "prueba"));
        expenses.add(new Expense(new BigDecimal(new Random().nextInt(2000 - 999) + 999), "prueba"));
        expenses.add(new Expense(new BigDecimal(new Random().nextInt(100 - 10) + 10), "prueba"));
        expenses.add(new Expense(new BigDecimal(new Random().nextInt(100 - 10) + 10), "prueba"));
        expenses.add(new Expense(new BigDecimal(new Random().nextInt(100 - 10) + 10), "prueba"));
    }

    public String getName() {
        return name;
    }

    public void addExpense(@NonNull BigDecimal amount, @NonNull String description) {
        expenses.add(new Expense(amount, description));
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", expenses=" + expenses +
                '}';
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
}
