package com.ksss.splintter.features.group.domain;

import android.support.annotation.NonNull;
import io.realm.RealmObject;
import java.util.Calendar;

/**
 * Created by Nahuel Barrios on 7/17/16.
 */
public class Expense extends RealmObject {

    private Float amount;
    private String description;

    private long date;

    private Group group;

    /**
     * Default constructor is required by Realm.
     */
    public Expense() {
        // Do nothing.
    }

    public Expense(@NonNull final Float amount, @NonNull final String description, final Group group) {
        this.amount = amount;
        this.description = description;
        this.group = group;
        date = System.currentTimeMillis();
    }

    public Float getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Calendar getDate() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar;
    }

    @Override
    public String toString() {
        return "Expense{" +
            "amount=" + amount +
            ", description='" + description + '\'' +
            '}';
    }
}
