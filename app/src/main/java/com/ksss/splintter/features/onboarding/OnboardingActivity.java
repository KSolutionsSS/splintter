package com.ksss.splintter.features.onboarding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ksss.splintter.Intents;
import com.ksss.splintter.R;

/**
 * Created by Nahuel Barrios on 7/16/16.
 */
public class OnboardingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.onboarding_layout);
    }

    public void createGroup(View view) {
        startActivity(Intents.GROUPS_CREATE.get());
    }
}
