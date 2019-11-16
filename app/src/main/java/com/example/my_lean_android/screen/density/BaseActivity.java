package com.example.my_lean_android.screen.density;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //设置density
//        DensityUtils.setDensity(getApplication(), this);
    }
}
