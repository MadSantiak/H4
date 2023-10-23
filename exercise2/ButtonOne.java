package com.example.exercise2;

import android.view.View;

public class ButtonOne implements View.OnClickListener {
    MainActivity main;
    public ButtonOne(MainActivity mainActivity)
    {
        main = mainActivity;
    }

    @Override
    public void onClick(View view) {
        main.input_data = main.editText.getText().toString();
    }
}
