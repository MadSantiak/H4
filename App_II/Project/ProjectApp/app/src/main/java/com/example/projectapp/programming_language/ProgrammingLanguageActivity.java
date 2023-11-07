package com.example.projectapp.programming_language;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.projectapp.R;
import com.example.projectapp.controllers.ApiLayer;
import com.example.projectapp.haircolor.Haircolor;
import com.example.projectapp.haircolor.HaircolorAdapter;

import java.util.ArrayList;
import java.util.List;

public class ProgrammingLanguageActivity extends AppCompatActivity {

    List<ProgrammingLanguage> proglangs = new ArrayList<>();
    ListView listHc;

    Button btnBack;
    ProgrammingLanguageAdapter haircolorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programming_language);
        proglangs = ApiLayer.getAllProgrammingLanguage();
        listHc = findViewById(R.id.listPl);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });


        haircolorAdapter = new ProgrammingLanguageAdapter(proglangs, this);

        listHc.setAdapter(haircolorAdapter);
    }
}