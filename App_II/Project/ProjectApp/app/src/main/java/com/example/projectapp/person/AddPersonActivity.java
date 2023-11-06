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
import android.widget.Spinner;
import android.widget.TextView;

import com.example.projectapp.controllers.ApiLayer;
import com.example.projectapp.R;
import com.example.projectapp.haircolor.Haircolor;

import java.io.Serializable;
import java.util.List;

public class AddPersonActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtName, txtPhone, txtNote, txtAddress;
    TextView txtId;
    CheckBox isFavorite;
    Button btnCreate, btnBack;
    Spinner spnHaircolor;
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
        spnHaircolor = findViewById(R.id.spnHaircolor);

        List<Haircolor> haircolors = ApiLayer.getAllHaircolor();
        ArrayAdapter<Haircolor> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, haircolors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnHaircolor.setAdapter(adapter);

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
            int haircolor_id = hc.getId();

            p = new Person(name, phone, address, note, favorite, haircolor_id);
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