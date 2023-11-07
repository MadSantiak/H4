package com.example.projectapp.programming_language;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projectapp.R;
import com.example.projectapp.controllers.ApiLayer;
import com.example.projectapp.haircolor.Haircolor;

import java.io.Serializable;

public class AddProgrammingLanguageActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtPlId;
    EditText txtPlName;
    Button btnPlCreate,btnPlBack;
    ProgrammingLanguage pl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_programming_language);

        txtPlId = findViewById(R.id.txtPlId);
        txtPlName = findViewById(R.id.txtPlName);
        btnPlCreate = findViewById(R.id.btnPlCreate);
        btnPlBack = findViewById(R.id.btnPlBack);

        btnPlCreate.setOnClickListener(this);
        btnPlBack.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == btnPlCreate) {
            String name = txtPlName.getText().toString();
            pl = new ProgrammingLanguage(name);
            Integer i = ApiLayer.addProgrammingLanguage(pl);
            txtPlId.setText(String.valueOf(i));

        }
        else if (v == btnPlBack) {
            Intent intent = new Intent();
            if (pl != null) {
                intent.putExtra("newProgrammingLanguage", (Serializable) pl);
                setResult(Activity.RESULT_OK, intent);
            }
            else {
                setResult(Activity.RESULT_CANCELED, intent);
            }
            finish();
        }

    }
}