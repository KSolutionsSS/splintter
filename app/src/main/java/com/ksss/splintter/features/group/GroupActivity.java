package com.ksss.splintter.features.group;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.ksss.splintter.R;
import com.ksss.splintter.features.group.domain.Group;
import com.ksss.splintter.features.group.view.GroupExpensesFragment;
import com.ksss.splintter.features.group.view.GroupExpensesSummaryFragment;

import timber.log.Timber;

/**
 * Created by Nahuel Barrios on 7/16/16.
 */
public class GroupActivity extends AppCompatActivity {

    private Group group;

    private ViewPager viewPager;

    private enum ViewMode {
        CREATE("create"), VIEW("view");

        private final String id;

        ViewMode(String action) {
            this.id = action;
        }

        private static ViewMode from(String action) {
            ViewMode result = VIEW;

            for (ViewMode each : values()) {
                if (each.id.equals(action)) {
                    result = each;
                    break;
                }
            }

            return result;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewMode mode = ViewMode.from(getIntent().getData().getPathSegments().get(0));

        if (mode == ViewMode.CREATE) {
            handleCreate();
        }

        handleView();
    }

    private void handleView() {
        Timber.d("Creating ViewPager...");
        setContentView(R.layout.group_view);

        createViewPager();
    }

    private void createViewPager() {
        GroupPagerAdapter pagerAdapter = new GroupPagerAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
    }

    private void handleCreate() {
        final EditText input = new EditText(this);
        input.setSingleLine();
        input.setHint(R.string.create_group_dialog_hint);
        new AlertDialog.Builder(this)
                .setView(input)
                .setTitle(R.string.create_group_dialog_title)
                .setPositiveButton(R.string.create_group_dialog_positive_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Timber.e("Creating group as: %s", input.getText());
                        group = new Group(input.getText().toString());
                        getSupportActionBar().setTitle(group.getName());
                    }
                })
                .setNegativeButton(R.string.create_group_dialog_negative_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        onBackPressed();
                    }
                }).show();
    }

    private class GroupPagerAdapter extends FragmentPagerAdapter {

        private GroupPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment result;

            switch (position) {
                case 0:
                    result = new GroupExpensesFragment();
                    break;
                case 1:
                    result = new GroupExpensesSummaryFragment();
                    break;
                default:
                    Timber.e("It's all wrong!!");
                    result = new GroupExpensesFragment();
            }

            return result;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            int result;
            switch (position) {
                case 0:
                    result = R.string.view_group_expenses_pager_title;
                    break;
                case 1:
                    result = R.string.view_group_expenses_summary_pager_title;
                    break;
                default:
                    Timber.e("It's all wrong!!");
                    result = R.string.view_group_expenses_pager_title;
            }

            return getText(result);
        }
    }

}
