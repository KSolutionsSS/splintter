package com.ksss.splintter.data.model;

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

    private Person person;

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

    public Group getGroup() {
        return group;
    }

    public void setPerson(final Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return "Expense{" +
            "amount=" + amount +
            ", description='" + description + '\'' +
            ", date=" + date +
            ", group=" + group +
            ", person=" + person +
            '}';
    }
}
