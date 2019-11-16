package com.example.my_lean_android.screen;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.my_lean_android.R;

public class MainPXActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_px_main);
        final TextView textView = findViewById(R.id.textView);
        textView.post(new Runnable() {
            @Override
            public void run() {
                Log.e("textView", textView.getWidth() + "  " + textView.getHeight());
                Log.e("layout", ((ScreenAdapterLayout) textView.getParent()).getWidth() +
                        "  " + ((ScreenAdapterLayout) textView.getParent()).getHeight());
            }
        });
    }
}
