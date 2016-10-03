package com.ksss.splintter;

import com.ksss.splintter.features.group.backend.GroupBo;
import com.ksss.splintter.features.group.backend.exception.EmptyNameException;
import com.ksss.splintter.features.group.backend.exception.NameTooShortException;
import com.ksss.splintter.features.group.backend.impl.GroupBoImpl;
import com.ksss.splintter.features.group.backend.impl.PersonBoImpl;
import com.ksss.splintter.features.group.domain.Expense;
import com.ksss.splintter.features.group.domain.Group;
import com.ksss.splintter.features.group.domain.Person;
import hugo.weaving.DebugLog;
import io.realm.Realm;
import io.realm.RealmList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import timber.log.Timber;

/**
 * Created by Nahuel Barrios on 7/20/16.
 */
final class DummyDatabaseHelper {

    /**
     * Required to check database status at {@link #check()}.
     */
    private static final int EXPENSES_BY_PERSON = 5;

    /**
     * Used only at {@link #check()}.
     */
    private static int totalPersons;

    /**
     * Used only at {@link #check()}.
     */
    private static int totalGroups;

    /* default */ enum Status {
        CLEAN, POPULATED, PREVIOUS_SESSION
    }

    private DummyDatabaseHelper() {
        // Do nothing.
    }

    static void clean() {
        final Realm db = Realm.getDefaultInstance();

        db.beginTransaction();
        db.deleteAll();
        db.commitTransaction();
        db.close();
    }

    static void populate() {
        final List<Group> groups = createDummyGroups();
        totalGroups = groups.size();

        totalPersons = createDummyPersons(groups);

        final Realm db = Realm.getDefaultInstance();
        db.beginTransaction();

        // Since groups contains persons, and persons contains expenses we don't need to insert anything else.
        db.insert(groups);

        db.commitTransaction();
        db.close();
    }

    @DebugLog
    private static List<Group> createDummyGroups() {
        final List<Group> dummyGroups = new ArrayList<>();

        final GroupBo groupBo = new GroupBoImpl();

        try {
            dummyGroups.add(groupBo.create("Mobile"));
            dummyGroups.add(groupBo.create("Football on thursday"));
            dummyGroups.add(groupBo.create("Dinner on friday"));
        } catch (EmptyNameException | NameTooShortException e) {
            Timber.e("Can't populate database with dummy data: %s", e.getMessage());
        }

        return dummyGroups;
    }

    @DebugLog
    private static int createDummyPersons(final List<Group> dummyGroups) {
        final Collection<Person> persons = new RealmList<>();
        try {
            persons.add(createPersonWithExpenses(dummyGroups, "Babu"));
            persons.add(createPersonWithExpenses(dummyGroups, "Caro"));
            persons.add(createPersonWithExpenses(dummyGroups, "Fede"));
            persons.add(createPersonWithExpenses(dummyGroups, "Juli"));
            persons.add(createPersonWithExpenses(dummyGroups, "Mati"));
            persons.add(createPersonWithExpenses(dummyGroups, "Mauri"));
            persons.add(createPersonWithExpenses(dummyGroups, "Nahu"));
            persons.add(createPersonWithExpenses(dummyGroups, "Paul"));
            persons.add(createPersonWithExpenses(dummyGroups, "Tincho"));
            persons.add(createPersonWithExpenses(dummyGroups, "Will"));
        } catch (final Throwable e) {
            Timber.e(e, "Can't populate database with dummy data: %s", e.getMessage());
        }

        return persons.size();
    }

    /**
     * Check dummy database population.
     */
    static void check() {
        final Realm db = Realm.getDefaultInstance();

        final int totalGroups = db.where(Group.class).findAll().size();
        if (totalGroups != DummyDatabaseHelper.totalGroups) {
            throw new AssertionError(
                "Database is corrupted. Expected " + DummyDatabaseHelper.totalGroups + " groups, found: " +
                    totalGroups);
        }

        final int totalPersons = db.where(Person.class).findAll().size();
        if (totalPersons != DummyDatabaseHelper.totalPersons) {
            throw new AssertionError(
                "Database is corrupted. Expected " + DummyDatabaseHelper.totalPersons + " persons, found: " +
                    totalPersons);
        }

        final int totalExpenses = db.where(Expense.class).findAll().size();
        if (totalExpenses != EXPENSES_BY_PERSON * totalPersons) {
            throw new AssertionError(
                "Database is corrupted. Expected " + EXPENSES_BY_PERSON * totalPersons + " expenses, found: " +
                    totalExpenses);
        }
    }

    private static Person createPersonWithExpenses(final List<Group> groups, final String name)
        throws EmptyNameException, NameTooShortException {

        final Person person = new PersonBoImpl().create(name);

        final List<Expense> expenses = new RealmList<>();

        for (int i = 0; i < EXPENSES_BY_PERSON; i++) {
            expenses.add(findGroupForExpense(groups, person));
        }

        person.setExpenses(expenses);

        return person;
    }

    private static Expense findGroupForExpense(final List<Group> groups, final Person person) {
        final Group randomGroup = getRandomGroup(groups);

        if (!randomGroup.getPersons().contains(person)) {
            Timber.d("Adding %s to group: %s", person.getName(), randomGroup.getName());
            randomGroup.addPerson(person);
        }

        return createRandomExpense(randomGroup);
    }

    private static Expense createRandomExpense(final Group group) {
        final Float randomAmount = (float) randomBetween(1000, 10);
        return new Expense(
            randomAmount
            , "Dummy description for amount " + randomAmount
            , group);
    }

    @DebugLog
    private static Group getRandomGroup(final List<Group> groups) {
        return groups.get(randomBetween(groups.size(), 1) - 1);
    }

    @DebugLog
    private static int randomBetween(final int max, final int min) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}
