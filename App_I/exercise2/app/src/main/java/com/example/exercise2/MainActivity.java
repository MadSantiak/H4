package com.example.exercise2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btn1;
    Button btn2;
    EditText editText;

    String input_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn_save);
        btn2 = findViewById(R.id.btn_load);
        editText = findViewById(R.id.editText);

        btn1.setOnClickListener(new ButtonOne(this));
        btn2.setOnClickListener(new ButtonTwo(this));
    }
}