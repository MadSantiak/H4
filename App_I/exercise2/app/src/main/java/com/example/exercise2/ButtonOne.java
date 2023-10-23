package com.example.exercise2;

import android.util.Log;
import android.view.View;

public class ButtonOne implements View.OnClickListener {
    MainActivity main;
    public ButtonOne(MainActivity mainActivity)
    {
        main = mainActivity;
    }

    @Override
    public void onClick(View view) {
        Log.e("Testing", "test");
        String test = main.editText.getText().toString();
        Log.e("Test2", test);
        main.input_data = main.editText.getText().toString();
    }
}
