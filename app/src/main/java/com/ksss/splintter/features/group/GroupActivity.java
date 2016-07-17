package com.ksss.splintter.features.group;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.ksss.splintter.R;
import com.ksss.splintter.features.group.domain.Group;
import com.ksss.splintter.features.group.view.GroupPagerAdapter;

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

}
