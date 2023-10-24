package com.example.calc_same;

import android.util.Log;
import android.view.View;

public class Operations implements View.OnClickListener {

    MainActivity main;
    int x, y;
    public Operations(MainActivity mainAct) {
        main = mainAct;
    }

    @Override
    public void onClick(View v) {
        x = (!main.variable_one.getText().toString().equals("")) ? Integer.parseInt(main.variable_one.getText().toString()) : 0;
        y = (!main.variable_two.getText().toString().equals("")) ? Integer.parseInt(main.variable_two.getText().toString()) : 0;

        if (v.getId() == R.id.btn_operation) {
            main.result.setText(String.valueOf(x + y));
        }

        if (v == main.subtract) {
            main.result.setText(String.valueOf(x - y));
        }

        if (v == main.multiply) {
            main.result.setText(String.valueOf(x * y));
        }
        if (v == main.findViewById(R.id.btn_divide)) {
            main.result.setText(String.valueOf(x / y));
        }
    }
}
