package com.example.movingpicture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout linLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linLayout = findViewById(R.id.linLayout);

        MyGraphics mg = new MyGraphics(this);
        linLayout.addView(mg);

        Thread t = new Thread(mg);
        t.start(); // Calls "run()" in the object cast to Thread, i.e. "mg".
    }
}