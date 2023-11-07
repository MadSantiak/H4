package com.example.projectapp.haircolor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.projectapp.MainActivity;
import com.example.projectapp.R;
import com.example.projectapp.controllers.ApiLayer;
import com.example.projectapp.person.PersonAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HaircolorActivity extends AppCompatActivity {

    List<Haircolor> haircolors = new ArrayList<>();
    ListView listHc;
    Button btnBack;

    HaircolorAdapter haircolorAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haircolor);
        haircolors = ApiLayer.getAllHaircolor();
        listHc = findViewById(R.id.listHc);
        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        haircolorAdapter = new HaircolorAdapter(haircolors, this);

        listHc.setAdapter(haircolorAdapter);
    }
}