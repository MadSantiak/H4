package com.example.eventhandlers;

import android.view.View;

public class MyHandler implements View.OnClickListener
{
    MainActivity main;

    public MyHandler(MainActivity mainActivity)
    {
        main = mainActivity;
    }

    @Override
    public void onClick(View v) {
        main.txtMyText.setText("New Text");
    }
}
