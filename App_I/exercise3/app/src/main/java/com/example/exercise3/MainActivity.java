package com.example.exercise3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText variable_one;
    EditText variable_two;

    EditText result;
    Button operation;
    Button subtract;
    int x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        variable_one = findViewById(R.id.var1);
        variable_two = findViewById(R.id.var2);
        result = findViewById(R.id.res);
        operation = findViewById(R.id.btn_operation);
        subtract = findViewById(R.id.btn_subtract);

        // Exercise 4
        subtract.setOnClickListener(new SubtractHandler());
        // Exercise 3
        operation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                x = (!variable_one.getText().toString().equals("")) ? Integer.parseInt(variable_one.getText().toString()) : 0;
                y = (!variable_two.getText().toString().equals("")) ? Integer.parseInt(variable_two.getText().toString()) : 0;
                result.setText(String.valueOf(x + y));
            }
        });
    }
    // Exercise 4
    private class SubtractHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            x = (!variable_one.getText().toString().equals("")) ? Integer.parseInt(variable_one.getText().toString()) : 0;
            y = (!variable_two.getText().toString().equals("")) ? Integer.parseInt(variable_two.getText().toString()) : 0;
            result.setText(String.valueOf(x-y));
        }
    }
}