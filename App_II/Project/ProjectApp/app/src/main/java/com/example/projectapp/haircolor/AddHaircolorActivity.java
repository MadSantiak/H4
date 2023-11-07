package com.example.projectapp.haircolor;

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
import com.example.projectapp.person.Person;
import com.example.projectapp.programming_language.ProgrammingLanguage;

import java.io.Serializable;

public class AddHaircolorActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtHcId;
    EditText txtHcName;
    Button btnHcCreate,btnHcBack;
    Haircolor hc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_haircolor);

        txtHcId = findViewById(R.id.txtHcId);
        txtHcName = findViewById(R.id.txtHcName);
        btnHcCreate = findViewById(R.id.btnHcCreate);
        btnHcBack = findViewById(R.id.btnHcBack);

        btnHcCreate.setOnClickListener(this);
        btnHcBack.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v == btnHcCreate) {
            String name = txtHcName.getText().toString();
            hc = new Haircolor(name);
            Integer i = ApiLayer.addHaircolor(hc);
            txtHcId.setText(String.valueOf(i));

        }
        else if (v == btnHcBack) {
            Intent intent = new Intent();
            if (hc != null) {
                intent.putExtra("newHaircolor", (Serializable) hc);
                setResult(Activity.RESULT_OK, intent);
            }
            else {
                setResult(Activity.RESULT_CANCELED, intent);
            }
            finish();
        }

    }
}