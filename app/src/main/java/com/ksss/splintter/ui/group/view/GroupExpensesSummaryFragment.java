package com.ksss.splintter.ui.group.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ksss.splintter.R;

/**
 * Created by Nahuel Barrios on 7/16/16.
 */
public class GroupExpensesSummaryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.wip, container, false);
    }

}
