package com.example.eventhandlers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Good practice to define variables as fields
    Button change_color;
    Button click_button;
    boolean Skift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Skift = false;
        // Count clicks on button (moved to internal anonymous)
        click_button = findViewById(R.id.count_click);
        click_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String count_string = click_button.getText().toString();
                int count_int = Integer.parseInt(count_string) + 1;
                click_button.setText(String.valueOf(count_int));
            };
        });
        // Change background on button click.
        change_color = findViewById(R.id.btnMyButton);
        change_color.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView myTxt = (TextView) findViewById(R.id.txtMyText);
                Log.d("Skift:", String.valueOf(Skift));
                //if (myTxt.getText().toString() == "black") {
                if (Skift) {
                    myTxt.setBackgroundResource(R.color.white);
                    //myTxt.setText("white");
                    Skift = false;
                }
                else {
                    myTxt.setBackgroundResource(R.color.black);
                    //myTxt.setText("black");
                    Skift = true;
                }

            }
        });

        // Use the OnClick defined in MyHandler class, using the OnClickListener interface
        change_color.setOnClickListener(new MyHandler(this));
    }

    @Override
    public void onClick(View v) {
        String count_string = click_button.getText().toString();
        int count_int = Integer.parseInt(count_string) + 1;
        click_button.setText(String.valueOf(count_int));
    }
}