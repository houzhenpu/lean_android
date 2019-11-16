package com.example.my_lean_android.screen.density;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.my_lean_android.R;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void start(View view) {
        startActivity(new Intent(this, MainDensityActivity.class));
    }
}
