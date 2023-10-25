package com.example.calc_table;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText var1;
    EditText var2;

    RadioGroup opGrp;

    Button btnResult;
    TextView txtResult;

    CheckBox chkMethod;

    TableRow radioRow;
    TableRow spinnerRow;
    Spinner opSpn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        var1 = findViewById(R.id.var1);
        var2 = findViewById(R.id.var2);
        opGrp = findViewById(R.id.opGrp);
        btnResult = findViewById(R.id.btnResult);
        txtResult = findViewById(R.id.txtResult);
        chkMethod = findViewById(R.id.chkMethod);
        radioRow = findViewById(R.id.radioRow);
        spinnerRow = findViewById(R.id.spinnerRow);
        spinnerRow.setVisibility(View.INVISIBLE);
        opSpn = (Spinner) findViewById(R.id.opSpn);
        String[] ops = {"+", "-", "/", "*"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, ops);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        opSpn.setAdapter(adapter);

        opSpn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        chkMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox box = (CheckBox) v;
                if (box.isChecked()) {
                    radioRow.setVisibility(View.VISIBLE);
                    spinnerRow.setVisibility(View.INVISIBLE);
                } else {
                    radioRow.setVisibility(View.INVISIBLE);
                    spinnerRow.setVisibility(View.VISIBLE);
                }
            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox method = (CheckBox) findViewById(R.id.chkMethod);
                String operation;
                if (method.isChecked())
                {
                    int opId = opGrp.getCheckedRadioButtonId();
                    RadioButton opBtn = findViewById(opId);
                    operation = opBtn.getText().toString();
                }
                else
                {

                    operation = opSpn.getSelectedItem().toString();
                    Log.d("Operation", operation);
                }
                float result = 0;
                int x = Integer.parseInt(var1.getText().toString());
                int y = Integer.parseInt(var2.getText().toString());
                switch (operation) {
                    case "+":
                        result = x + y;
                        break;
                    case "-":
                        result = x - y;
                        break;
                    case "*":
                        result = x * y;
                        break;
                    case "/":
                        result = x / y;
                        break;
                }
                txtResult.setText(String.valueOf(result));
            }
        });

    }
}