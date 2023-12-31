package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create view programmatically:
        /**
        LinearLayout l = new LinearLayout(this);
        TextView txt = new TextView(this);
        Button btn = new Button(this);
        l.setOrientation(LinearLayout.VERTICAL);
        txt.setText("Test");
        btn.setText("KNAP");
        l.addView(txt);
        l.addView(btn);
        setContentView(l);
        **/

        // NOTE: The below code includes the first exercises
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