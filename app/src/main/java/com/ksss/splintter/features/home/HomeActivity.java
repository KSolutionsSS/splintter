package com.ksss.splintter.features.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ksss.splintter.R;
import com.ksss.splintter.databinding.HomeLayoutBinding;

/**
 * Created by Nahuel Barrios on 7/16/16.
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HomeLayoutBinding binding = DataBindingUtil.setContentView(this, R.layout.home_layout);
        binding.withDataBinding.setText(R.string.using_data_binding_value);
    }
}
