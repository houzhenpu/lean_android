package com.example.my_lean_android.screen.density;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.my_lean_android.R;

public class MainDensityActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //设置density
//        DensityUtils.setDensity(getApplication(), this);
        setContentView(R.layout.activity_density_main);
    }

    public void start(View view) {
        startActivity(new Intent(this, SecondActivity.class));
    }
}
