package com.example.time_teller;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

public class EndTime extends AppCompatActivity implements View.OnClickListener {
    TimePicker endTime;
    Button btnSetEnd;
    Button btnCancel;

    int endMin;
    int endHour;

    Boolean is24Hour;

    Intent mainIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_time);

        endTime = findViewById(R.id.endTime);
        endTime.setIs24HourView(true);
        btnSetEnd = findViewById(R.id.btnSetEnd);
        btnCancel = findViewById(R.id.btnCancel);

        btnSetEnd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        mainIntent = getIntent();
        is24Hour = mainIntent.getBooleanExtra("hourSet", false);
        endTime.setIs24HourView(is24Hour);
    }

    @Override
    public void onClick(View v) {
        if (v == btnSetEnd)
        {
            // Return starttime
            endHour = endTime.getCurrentHour();
            endMin = endTime.getCurrentMinute();
            setResult(Activity.RESULT_OK, mainIntent);
        }
        else
        {
            // Do nothing, cancel is just there for user friendliness
        }
        mainIntent.putExtra("endHour", endHour);
        mainIntent.putExtra("endMin", endMin);
        finish();
    }
}