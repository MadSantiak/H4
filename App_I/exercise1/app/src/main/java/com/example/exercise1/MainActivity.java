package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    boolean Skift;
    Button myButton;
    TextView myText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myButton = findViewById(R.id.btnMyButton);
        myText = findViewById(R.id.txtMyText);
        Skift = false;
        myButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String count_string = myButton.getText().toString();
        int count_int = Integer.parseInt(count_string) + 1;
        myButton.setText(String.valueOf(count_int));

        if (Skift) {
            myText.setBackgroundResource(R.color.white);
        }
        else {
            myText.setBackgroundResource(R.color.black);

        }
        Skift = !Skift;

    }
}