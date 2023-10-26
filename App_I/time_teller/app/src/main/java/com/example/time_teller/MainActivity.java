package com.example.time_teller;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btnStart, btnEnd;

    TextView sumDec, sumTime, startTime, endTime;

    CheckBox hourSet;

    ActivityResultLauncher<Intent> startTimeActivityLauncher;
    ActivityResultLauncher<Intent> endTimeActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnEnd = findViewById(R.id.btnEnd);
        sumDec = findViewById(R.id.sumDec);
        sumTime = findViewById(R.id.sumTime);
        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);
        hourSet = findViewById(R.id.hourSet);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startTimeIntent = new Intent(MainActivity.this, StartTime.class);
                startTimeIntent.putExtra("hourSet", hourSet.isChecked());
                startTimeActivityLauncher.launch(startTimeIntent);
            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent endTimeIntent = new Intent(MainActivity.this, EndTime.class);
                endTimeIntent.putExtra("hourSet", hourSet.isChecked());
                endTimeActivityLauncher.launch(endTimeIntent);
            }
        });

        startTimeActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Log.d("Result", String.valueOf(result.getResultCode()));
                        if (result.getResultCode() == Activity.RESULT_OK)
                        {
                            Intent data = result.getData();
                            int hour = data.getIntExtra("startHour", 0);
                            int min = data.getIntExtra("startMin", 0);
                            String time = hour + ":" + min;
                            Log.d("Time:", time);
                            startTime.setText(time);
                            if (endTime.getText().toString() != "")
                            {
                                CalcTime();
                            }
                        }
                    }
                }
        );
        endTimeActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK)
                        {
                            Intent data = result.getData();
                            int hour = data.getIntExtra("endHour", 0);
                            int min = data.getIntExtra("endMin", 0);
                            String time = hour + ":" + min;
                            endTime.setText(time);
                            if (startTime.getText().toString() != "")
                            {
                                CalcTime();
                            }
                        }
                    }
                }
        );
    }

    public void CalcTime() {
        String strStart = startTime.getText().toString();
        String strEnd = endTime.getText().toString();

        SimpleDateFormat simple = (hourSet.isChecked()) ? (new SimpleDateFormat("HH:mm")) : (new SimpleDateFormat("h:mm a"));
        Log.d("Format", simple.toString());
        try
        {
            Date start = simple.parse(strStart);
            Date end = simple.parse(strEnd);
            long diffMilli = end.getTime() - start.getTime();

            float floatTimeDiff = (float) diffMilli / (60*60*1000);

            long longHourDiff = diffMilli / (60*60*1000);
            long longMinDiff = (diffMilli / (60*1000)) % 60;
            String strLongDiff = longHourDiff + ":" + longMinDiff;
            sumDec.setText(String.valueOf(floatTimeDiff));
            sumTime.setText(strLongDiff);
            View mainView = findViewById(R.id.mainView);
            if (floatTimeDiff > 8)
            {
                mainView.setBackgroundColor(getResources().getColor(R.color.red_warn));
            }
            else if (floatTimeDiff > 0)
            {
                mainView.setBackgroundColor(getResources().getColor(R.color.green_good));
            }
            else
            {
                mainView.setBackgroundColor(getResources().getColor(R.color.white));
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
}