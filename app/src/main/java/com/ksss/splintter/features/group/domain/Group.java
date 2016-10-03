package com.ksss.splintter.features.group.domain;

import android.support.annotation.NonNull;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import java.util.List;

/**
 * // TODO: 7/17/16 Add auto-value!(?)
 * Created by Nahuel Barrios on 7/17/16.
 */
public class Group extends RealmObject {

    @PrimaryKey
    private String name;

    private RealmList<Person> persons;

    /**
     * Default constructor is required by Realm.
     */
    public Group() {
        // Do nothing.
    }

    public Group(@NonNull final String name) {
        this.name = name;
        persons = new RealmList<>();
    }

    public String getName() {
        return name;
    }

    @NonNull
    public List<Person> getPersons() {
        return persons;
    }

    public void addPerson(final Person person) {
        persons.add(person);
    }

    @Override
    public String toString() {
        return "Group{" +
            "name='" + name + '\'' +
            ", persons=" + persons +
            '}';
    }
}
