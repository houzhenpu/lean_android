package com.example.my_lean_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my_lean_android.paint.GradientLineViewActivity;
import com.example.my_lean_android.paint.GradientViewActivity;
import com.example.my_lean_android.xfermode.XfermodeActivity;

public class MainActivity extends AppCompatActivity {

    View.OnClickListener onClickListener = view -> {
        switch (view.getId()) {
            case R.id.paint:
                startActivity(new Intent(getBaseContext(), GradientViewActivity.class));
                break;
            case R.id.paint_line:
                startActivity(new Intent(getBaseContext(), GradientLineViewActivity.class));
                break;
            case R.id.XfermodeActivity:
                startActivity(new Intent(getBaseContext(), XfermodeActivity.class));
                break;
            default:
                break;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.paint).setOnClickListener(onClickListener);
        findViewById(R.id.paint_line).setOnClickListener(onClickListener);
        findViewById(R.id.XfermodeActivity).setOnClickListener(onClickListener);

    }
}
