package com.ksss.splintter.features.group.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.MaterialIcons;
import com.ksss.splintter.R;
import com.ksss.splintter.features.group.domain.Group;
import com.ksss.splintter.features.group.domain.Member;

import java.math.BigDecimal;

import hugo.weaving.DebugLog;

/**
 * Created by Nahuel Barrios on 7/16/16.
 */
public class GroupExpensesFragment extends Fragment {

    private Group group;

    private AlertDialog alertDialog;

    private boolean setupAutocomplete = true;

    private AutoCompleteTextView memberEditText;
    private AutoCompleteTextView descriptionEditText;
    private EditText amountEditText;

    private View layout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // TODO: 7/17/16 What about changing the AlertDialog for an editable row in the RecyclerView?
        layout = inflater.inflate(R.layout.group_expenses_layout, container, false);

        IconDrawable iconDrawable = new IconDrawable(getActivity(), MaterialIcons.md_plus_one);
        iconDrawable.colorRes(android.R.color.white);

        FloatingActionButton fab = (FloatingActionButton) layout.findViewById(R.id.fab);
        fab.setImageDrawable(iconDrawable);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alertDialog == null) {
                    buildAddExpenseDialog();
                }

                showDialog();
            }
        });

        return layout;
    }

    /**
     * Build the alert dialog to prevent building when the user is wants to use it.
     */
    @Override
    public void onResume() {
        super.onResume();
        buildAddExpenseDialog();
    }

    @DebugLog
    private void buildAddExpenseDialog() {
        alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.add_expense_dialog_title)
                .setView(R.layout.group_view_add_expense)
                .setPositiveButton(R.string.new_expense_dialog_positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Member member = ((MembersAdapter) GroupExpensesFragment.this.memberEditText.getAdapter()).findByName(GroupExpensesFragment.this.memberEditText.getText().toString());

                        group = getCallback().getGroup();
                        if (member == null) {
                            // TODO: 7/17/16 Make a class diagram taking into account that members will share groups so we must know which expense is related to which group
                            member = new Member(GroupExpensesFragment.this.memberEditText.getText().toString());
                            group.addMember(member);
                        }

                        member.addExpense(new BigDecimal(amountEditText.getText().toString()), descriptionEditText.getText().toString());
                        // TODO: 7/17/16 What about Realm.io?


                        // TODO: 7/17/16 Come on dude! Let's use a RecyclerView =)
                        TextView textView = new TextView(getActivity());
                        textView.setText(member.getName() + ": " + amountEditText.getText().toString());
                        LinearLayout list = (LinearLayout) layout.findViewById(R.id.prueba);
                        list.addView(textView);

                        clearDialog();
                    }
                })
                .setNegativeButton(R.string.create_group_dialog_negative_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        clearDialog();
                    }
                }).create();
    }

    /**
     * Wrapper for {@link AlertDialog#show()} because we need to populate the {@link AutoCompleteTextView} we're using to display group members.
     */
    private void showDialog() {
        alertDialog.show();
        if (setupAutocomplete) {
            setupMembersAutocomplete();
            setupDescriptionAutocomplete();
            amountEditText = (EditText) alertDialog.findViewById(R.id.add_expense_amount);
        }
    }

    private void setupDescriptionAutocomplete() {
        descriptionEditText = (AutoCompleteTextView) alertDialog.findViewById(R.id.add_expense_description);

        // TODO: 7/17/16 Make it dynamic! realm.io comes to playground again =)
        String[] hardCodedSuggestions = {"Bebida", "Nafta", "Pizzas"};

        // TODO: 7/17/16 I don't need an adapter like MembersAdapter here because descriptions are just a simple String
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, hardCodedSuggestions);
        descriptionEditText.setAdapter(adapter);
    }

    /**
     * Clear {@link AlertDialog} layout views because we're building it just once so the next time you call {@link AlertDialog#show()}
     * views must not contain any value.
     */
    private void clearDialog() {
        memberEditText.setText("");
        amountEditText.setText("");
        descriptionEditText.setText("");
    }

    private void setupMembersAutocomplete() {
        memberEditText = (AutoCompleteTextView) alertDialog.findViewById(R.id.add_expense_person);

        memberEditText.setAdapter(new MembersAdapter(getContext(), getCallback().getGroup().getMembers()));
        setupAutocomplete = false;
    }

    /**
     * // TODO: 7/17/16 I can't stand with this
     *
     * @return
     */
    private ExpenseManager getCallback() {
        ExpenseManager callback;
        try {
            callback = ((ExpenseManager) getActivity());
        } catch (ClassCastException e) {
            throw new IllegalStateException(String.format("Container Activity must implement %s", ExpenseManager.class.getSimpleName()));
            // TODO: 7/17/16 Add log?
        }

        return callback;
    }

    /**
     * I don't want to create any toString, let's use auto value!!
     *
     * @return
     */
    @Override
    public String toString() {
        return "GroupExpensesFragment{" +
                "setupMembersAutocomplete=" + setupAutocomplete +
                '}';
    }
}
