package com.example.exercise5;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn_rnd;
    TextView result;
    TextView message;

    Random rnd = new Random();

    boolean tic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tic = false;
        LinearLayout l = findViewById(R.id.main_layout);
        btn_rnd = findViewById(R.id.btn_random);
        result = findViewById(R.id.number);
        message = findViewById(R.id.message_text);

        String game_over = getResources().getString(R.string.game_over);
        String game_on = getResources().getString(R.string.game_on);
        int red = getColor(R.color.red);
        int green = getColor(R.color.green);
        int blue = getColor(R.color.blue);

        btn_rnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int number = rnd.nextInt(6);
                if (number == 3)
                {
                    message.setText(game_over);
                    l.setBackgroundColor(red);
                    tic = false;
                }
                else {
                    message.setText(game_on);
                    int color = (tic) ? blue : green;
                    l.setBackgroundColor(color);
                    tic = !tic;

                }
            }
        });


    }
}