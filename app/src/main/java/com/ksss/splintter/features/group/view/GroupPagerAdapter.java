package com.ksss.splintter.features.group.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import timber.log.Timber;

/**
 * Created by Nahuel Barrios on 7/17/16.
 */

public class GroupPagerAdapter extends FragmentPagerAdapter {

    public GroupPagerAdapter(FragmentManager fm) {
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
                result = new GroupExpensesSummaryFragment();
        }

        return result;
    }
}
