package com.example.exercise2;

import android.view.View;

public class ButtonTwo implements View.OnClickListener {
    MainActivity main;
    public ButtonTwo(MainActivity mainActivity)
    {
        main = mainActivity;
    }

    @Override
    public void onClick(View view) {
        main.editText.setText(main.input_data);
    }
}
