package com.example.calc_diff;

import android.view.View;

public class MultiplicationHandler implements View.OnClickListener {
    MainActivity main;
    public MultiplicationHandler(MainActivity mainActivity) {
        main = mainActivity;
    }


    @Override
    public void onClick(View v) {
        main.x = (!main.variable_one.getText().toString().equals("")) ? Integer.parseInt(main.variable_one.getText().toString()) : 0;
        main.y = (!main.variable_two.getText().toString().equals("")) ? Integer.parseInt(main.variable_two.getText().toString()) : 0;
        main.result.setText(String.valueOf(main.x * main.y));
    }
}
