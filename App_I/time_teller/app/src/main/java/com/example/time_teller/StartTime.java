package com.example.time_teller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class StartTime extends AppCompatActivity implements View.OnClickListener {
    TimePicker startTime;
    Button btnSetStart;
    Button btnCancel;
    Intent mainIntent;

    int startHour;
    int startMin;
    Boolean is24Hour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_time);

        startTime = findViewById(R.id.startTime);
        startTime.setIs24HourView(true);
        btnSetStart = findViewById(R.id.btnSetStart);
        btnCancel = findViewById(R.id.btnCancel);

        btnSetStart.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        mainIntent = getIntent();
        is24Hour = mainIntent.getBooleanExtra("hourSet", false);
        startTime.setIs24HourView(is24Hour);
    }

    @Override
    public void onClick(View v) {
        if (v == btnSetStart)
        {
            // Return starttime
            startHour = startTime.getCurrentHour();
            startMin = startTime.getCurrentMinute();

            setResult(Activity.RESULT_OK, mainIntent);
        }
        else
        {
            // No need to fill this out, but just to avoid user confusion.
        }
        mainIntent.putExtra("startHour", startHour);
        mainIntent.putExtra("startMin", startMin);
        finish();
    }
}