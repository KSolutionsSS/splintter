package com.ksss.splintter.ui.group.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import com.ksss.splintter.data.model.Person;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nahuel Barrios on 7/17/16.
 */
public class MembersAdapter extends ArrayAdapter<String> {

    private final List<Person> persons;

    /* default */ MembersAdapter(Context context, List<Person> persons) {
        super(context, android.R.layout.simple_dropdown_item_1line, parse(persons));
        this.persons = persons;
    }

    private static List<String> parse(List<Person> persons) {
        List<String> membersName = new ArrayList<>();

        for (Person eachPerson : persons) {
            membersName.add(eachPerson.getName());
        }

        return membersName;
    }

    @Nullable
    /* default */ Person findByName(@NonNull String name) {
        Person result = null;
        for (Person eachPerson : persons) {
            if (eachPerson.getName().equalsIgnoreCase(name)) {
                result = eachPerson;
                break;
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return "MembersAdapter{" +
            "persons=" + persons +
                '}';
    }
}
