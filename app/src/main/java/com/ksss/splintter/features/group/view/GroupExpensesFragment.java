package com.ksss.splintter.features.group.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.MaterialIcons;
import com.ksss.splintter.R;
import com.ksss.splintter.features.group.backend.ExpenseBo;
import com.ksss.splintter.features.group.backend.PersonBo;
import com.ksss.splintter.features.group.backend.exception.EmptyNameException;
import com.ksss.splintter.features.group.backend.exception.NameTooShortException;
import com.ksss.splintter.features.group.backend.impl.ExpenseBoImpl;
import com.ksss.splintter.features.group.backend.impl.PersonBoImpl;
import com.ksss.splintter.features.group.domain.Expense;
import com.ksss.splintter.features.group.domain.Group;
import com.ksss.splintter.features.group.domain.Person;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import hugo.weaving.DebugLog;
import io.realm.Realm;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import timber.log.Timber;

/**
 * Created by Nahuel Barrios on 7/16/16.
 */
public class GroupExpensesFragment extends Fragment {

    private Group group;

    private AutoCompleteTextView personEditText;
    private AutoCompleteTextView descriptionEditText;
    private EditText amountEditText;
    private RecyclerView expensesRecyclerView;

    private PersonBo personBo;
    private ExpenseBo expenseBo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personBo = new PersonBoImpl();
        expenseBo = new ExpenseBoImpl();

        group = getCallback().getGroup();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View layout = inflater.inflate(R.layout.group_expenses_layout, container, false);
        personEditText = (AutoCompleteTextView) layout.findViewById(R.id.add_expense_person);
        amountEditText = (EditText) layout.findViewById(R.id.add_expense_amount);
        descriptionEditText = (AutoCompleteTextView) layout.findViewById(R.id.add_expense_description);
        expensesRecyclerView = (RecyclerView) layout.findViewById(R.id.group_view_expenses_recycler_view);

        setupRecyclerView();

        final IconDrawable iconDrawable = new IconDrawable(getActivity(), MaterialIcons.md_plus_one);
        iconDrawable.colorRes(android.R.color.white);

        final FloatingActionButton floatingActionButton = (FloatingActionButton) layout.findViewById(R.id.fab);
        floatingActionButton.setImageDrawable(iconDrawable);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            @DebugLog
            @Override
            @SuppressFBWarnings(
                value = "UCC_UNRELATED_COLLECTION_CONTENTS"
                , justification = "UCC_UNRELATED_COLLECTION_CONTENTS: I think this is a Findbugs issue with Realm; "
            )
            public void onClick(final View view) {
//                 TODO: 10/2/16 This log is here only because I can't fix Findbugs warning UP_UNUSED_PARAMETER =(
                Timber.v("Tap on viewId: %s", view.getId());
                onNewExpenseAdded();
            }
        });

        return layout;
    }

    private void setupRecyclerView() {
        expensesRecyclerView.setHasFixedSize(true);

        expensesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        expensesRecyclerView.setAdapter(new ExpensesAdapter(getGroupExpenses()));
    }

    private List<Expense> getGroupExpenses() {

        final List<Expense> result = new ArrayList<>();

        for (final Person eachPerson : group.getPersons()) {
            for (final Expense eachExpense : eachPerson.getExpenses()) {
                result.add(eachExpense);
            }
        }

        return result;
    }

    @Override
    public void onResume() {
        super.onResume();

        setupMembersAutocomplete();
        setupDescriptionAutocomplete();
    }

    private void setupMembersAutocomplete() {
        personEditText.setAdapter(new MembersAdapter(getContext(), getCallback().getGroup().getPersons()));
    }

    @SuppressFBWarnings(value = "SACM_STATIC_ARRAY_CREATED_IN_METHOD", justification = "Still under development")
    private void setupDescriptionAutocomplete() {
        // TODO: 7/17/16 Make it dynamic! realm.io comes to playground again =)
        final String[] hardCodedSuggestions = { "Bebida", "Nafta", "Pizzas" };

        // TODO: 7/17/16 I don't need an adapter like MembersAdapter here because descriptions are just a simple String
        final ArrayAdapter<String> adapter =
            new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, hardCodedSuggestions);
        descriptionEditText.setAdapter(adapter);
    }

    /**
     * // TODO: 10/2/16 This behavior MUST be in a Business Object
     *
     * Package-protected because method is used from an inner/anonymous class.
     */
    /* default */ void onNewExpenseAdded() {
        Person person = ((MembersAdapter) personEditText.getAdapter()).findByName(personEditText.getText().toString());

        group = getCallback().getGroup();
        if (person == null) {
            // TODO: 7/17/16 Make a class diagram taking into account that members will share groups so we must know which expense is related to which group
            try {
                person = personBo.create(personEditText.getText().toString());

                // TODO: 7/19/16 Update person adapter!

            } catch (final EmptyNameException | NameTooShortException e) {
                Timber.e(e.getMessage());
            }

            if (person == null) {
                Timber.e("TODO: Show an error to the user!");
                // TODO: 10/2/16 Show error!
            } else {
                final Expense expense = expenseBo.create(
                    Float.valueOf(amountEditText.getText().toString())
                    , descriptionEditText.getText().toString()
                    , group
                );

                person.getExpenses().add(expense);
                group.addPerson(person);

                //        // TODO: 10/2/16 Persist persons from another thread!
                final Realm db = Realm.getDefaultInstance();
                db.beginTransaction();
                db.insertOrUpdate(group);
                db.commitTransaction();
            }
        }

    }

    /**
     * // TODO: 7/17/16 I can't stand with this
     *
     * @return
     */
    private ExpenseManager getCallback() {
        final ExpenseManager callback;
        try {
            callback = ((ExpenseManager) getActivity());
        } catch (final ClassCastException e) {
            throw new IllegalStateException(
                String.format("Container Activity must implement %s", ExpenseManager.class.getSimpleName()), e);
            // TODO: 7/17/16 Add log?
        }

        return callback;
    }

    private class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ViewHolder> {

        private final List<Expense> expenses;

        public ExpensesAdapter(@NonNull List<Expense> groupExpenses) {
            expenses = groupExpenses;
        }


        public class ViewHolder extends RecyclerView.ViewHolder {

            /***
             * Package-protected because method is used from an inner/anonymous class.
             */
            /* default */ final TextView person;

            /***
             * Package-protected because method is used from an inner/anonymous class.
             */
            /* default */ final TextView amount;

            /***
             * Package-protected because method is used from an inner/anonymous class.
             */
            /* default */ final TextView description;

            /***
             * Package-protected because method is used from an inner/anonymous class.
             */
            /* default */ final TextView date;

            public ViewHolder(final View layout) {
                super(layout);
                person = (TextView) layout.findViewById(R.id.expense_person);
                amount = (TextView) layout.findViewById(R.id.expense_amount);
                description = (TextView) layout.findViewById(R.id.expense_description);
                date = (TextView) layout.findViewById(R.id.expense_date);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
            return new ViewHolder(getActivity().getLayoutInflater().inflate(R.layout.group_view_expense, parent, false));
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final Expense expense = expenses.get(position);

            // TODO: 10/2/16 Stop hard-coding this value!
            holder.person.setText("Nahue");
            holder.amount.setText(expense.getAmount().toString());
            holder.description.setText(expense.getDescription());
            holder.date.setText(new SimpleDateFormat("MMMM, dd", Locale.getDefault()).format(expense.getDate().getTime()));
        }

        @Override
        public int getItemCount() {
            return expenses.size();
        }
    }

    @Override
    public String toString() {
        return "GroupExpensesFragment{" +
            "group=" + group +
            ", personEditText=" + personEditText +
            ", descriptionEditText=" + descriptionEditText +
            ", amountEditText=" + amountEditText +
            ", expensesRecyclerView=" + expensesRecyclerView +
            ", personBo=" + personBo +
            ", expenseBo=" + expenseBo +
            '}';
    }
}
