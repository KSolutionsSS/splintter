package com.ksss.splintter.features.group.domain;

import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 * Created by Nahuel Barrios on 7/17/16.
 */
public class Expense {

    private BigDecimal amount;
    private String description;
    private Calendar date;

    /* package */ Expense(@NonNull BigDecimal amount, @NonNull String description) {
        this.amount = amount;
        this.description = description;
        this.date = Calendar.getInstance();
    }

    @Override
    public String toString() {
        return "Expense{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
