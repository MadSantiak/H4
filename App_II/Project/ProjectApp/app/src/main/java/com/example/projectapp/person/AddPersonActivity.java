package com.example.projectapp.person;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.projectapp.controllers.ApiLayer;
import com.example.projectapp.R;
import com.example.projectapp.haircolor.Haircolor;
import com.example.projectapp.programming_language.ProgrammingLanguage;

import java.io.Serializable;
import java.util.List;

public class AddPersonActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtName, txtPhone, txtNote, txtAddress;
    TextView txtId;
    CheckBox isFavorite;
    Button btnCreate, btnBack;
    Spinner spnHaircolor;
    RadioGroup radPrg;

    RadioGroup radProgrammingLanguage;
    Person p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        txtId = findViewById(R.id.txtId);
        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtNote = findViewById(R.id.txtNote);
        txtAddress = findViewById(R.id.txtAddress);
        isFavorite = findViewById(R.id.isFavorite);
        btnCreate = findViewById(R.id.btnCreate);
        btnBack = findViewById(R.id.btnBack);
        // Establish and populate Spinner for Haircolor:
        spnHaircolor = findViewById(R.id.spnHaircolor);

        List<Haircolor> haircolors = ApiLayer.getAllHaircolor();
        ArrayAdapter<Haircolor> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, haircolors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnHaircolor.setAdapter(adapter);

        // Establish and populate RadioGroup with buttons equal to the amount of rows in DB:
        radPrg = findViewById(R.id.radPrg);
        List<ProgrammingLanguage> progLangs = ApiLayer.getAllProgrammingLanguage();
        for (int i = 0; i < progLangs.size(); i++) {
            RadioButton option = new RadioButton(this);
            option.setId(i+1);
            option.setText(progLangs.get(i).getName());
            radPrg.addView(option);
        }

        btnCreate.setOnClickListener(this);
        btnBack.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v == btnCreate) {
            String name = txtName.getText().toString();
            String phone = txtPhone.getText().toString();
            String note = txtNote.getText().toString();
            String address = txtAddress.getText().toString();
            boolean favorite = isFavorite.isChecked();

            Haircolor hc = (Haircolor) spnHaircolor.getSelectedItem();

            int programminglanguage_id = radPrg.getCheckedRadioButtonId();
            Log.d("ProgLang:", String.valueOf(programminglanguage_id));
            p = new Person(name, phone, address, note, favorite, hc, programminglanguage_id);
            Integer i = ApiLayer.addPerson(p);

            txtId.setText(i.toString());

        }
        else if (v == btnBack) {
            Intent intent = new Intent();
            intent.putExtra("newPerson", (Serializable) p);
            setResult(Activity.RESULT_OK, intent);
            finish();
        }

    }
}