package com.example.calc_same;

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
    Button multiply;
    Button divide;

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
        multiply = findViewById(R.id.btn_multiply);
        divide = findViewById(R.id.btn_divide);

        Operations op = new Operations(this);

        operation.setOnClickListener(op);
        subtract.setOnClickListener(op);
        multiply.setOnClickListener(op);
        divide.setOnClickListener(op);

    }
}