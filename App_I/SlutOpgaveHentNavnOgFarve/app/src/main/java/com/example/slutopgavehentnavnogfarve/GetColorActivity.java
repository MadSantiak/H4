package com.example.slutopgavehentnavnogfarve;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class GetColorActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView typeTitle, colorSample;
    Button btnSendColor;
    Spinner redSpn, greenSpn, blueSpn;

    Intent mainIntent;

    public static final String[] hexCode =
            {
            "00", "10", "20", "30", "40", "50", "60", "70", "80", "90","A0", "B0", "C0", "D0", "E0", "F0", "FF"
            };

    Color colorCode;
    String strColorCode;
    // For some reason serial-initialization caused one value to become null(?)
    String strRed, strBlue, strGreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_color);

        typeTitle = findViewById(R.id.typeTitle);
        btnSendColor = findViewById(R.id.btnSendColor);
        mainIntent = getIntent();

        String strType = mainIntent.getStringExtra("relationType");
        typeTitle.setText((strType != null) ? strType + "'s Color" : "Color");
        //typeTitle.setText(strType + "'s Color");
        strRed = "00";
        strBlue = "00";
        strGreen = "00";
        redSpn = findViewById(R.id.redSpn);
        greenSpn = findViewById(R.id.greenSpn);
        blueSpn = findViewById(R.id.blueSpn);
        colorSample = findViewById(R.id.colorSample);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hexCode);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        redSpn.setAdapter(adapter);
        greenSpn.setAdapter(adapter);
        blueSpn.setAdapter(adapter);

        redSpn.setOnItemSelectedListener(this);
        greenSpn.setOnItemSelectedListener(this);
        blueSpn.setOnItemSelectedListener(this);

        btnSendColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainIntent.putExtra("r", strRed);
                mainIntent.putExtra("g", strGreen);
                mainIntent.putExtra("b", strBlue);
                setResult(Activity.RESULT_OK, mainIntent);
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView == redSpn)
        {
            strRed = adapterView.getItemAtPosition(i).toString();
        }
        if (adapterView == greenSpn)
        {
            strGreen = adapterView.getItemAtPosition(i).toString();
        }
        if (adapterView == blueSpn)
        {
            strBlue = adapterView.getItemAtPosition(i).toString();
        }
        strColorCode = "#" + strRed + strGreen + strBlue;
        Log.d("Red", strRed);
        Log.d("Green", strGreen);
        Log.d("Blue", strBlue);
        Log.d("Code", strColorCode);
        colorSample.setBackgroundColor(Color.parseColor(strColorCode));
        colorSample.setText(strColorCode);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}