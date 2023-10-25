package com.example.second_act;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtFromMain;
    Button btnToMain;
    EditText txtToMain;
    Intent theIntent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtFromMain = findViewById(R.id.txtFromMain);
        btnToMain = findViewById(R.id.btnToMain);
        txtToMain = findViewById(R.id.txtToMain);
        btnToMain.setOnClickListener(this);

        theIntent = getIntent();
        String str = theIntent.getStringExtra(MainActivity.TEXT_FROM_MAIN);
        txtFromMain.setText(str);

        // Get seralizable object:
        Person p = (Person)theIntent.getSerializableExtra("PersonObj");
        txtFromMain.setText(p.name);
    }

    @Override
    public void onClick(View v) {
        theIntent.putExtra("Result", txtToMain.getText().toString());
        setResult(Activity.RESULT_OK, theIntent);

        // Ends the second activity:
        finish();


    }
}
