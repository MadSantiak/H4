package com.example.eventhandlers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button click_button = (Button) findViewById(R.id.count_click);

        click_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String count_string = click_button.getText().toString();
                int count_int = Integer.parseInt(count_string) + 1;
                click_button.setText(String.valueOf(count_int));
            };
        });

        Button change_color = (Button) findViewById(R.id.change_theme);
        change_color.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView myTxt = (TextView) findViewById(R.id.txtMyText);

                if (myTxt.getText().toString() == "black") {
                    myTxt.setBackgroundResource(R.color.white);
                    myTxt.setText("white");
                }
                else {
                    myTxt.setBackgroundResource(R.color.black);
                    myTxt.setText("black");
                }

            }
        });
    }
}