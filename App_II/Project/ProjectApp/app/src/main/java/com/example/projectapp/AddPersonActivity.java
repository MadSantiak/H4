package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.Serializable;

public class AddPersonActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtName, txtPhone, txtNote, txtAddress;
    CheckBox isFavorite;
    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtNote = findViewById(R.id.txtNote);
        txtAddress = findViewById(R.id.txtAddress);
        isFavorite = findViewById(R.id.isFavorite);
        btnCreate = findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String name = txtName.getText().toString();
        String phone = txtPhone.getText().toString();
        String note = txtNote.getText().toString();
        String address = txtAddress.getText().toString();
        boolean favorite = isFavorite.isChecked();

        Person p = new Person(name, phone, address, note, favorite);
        ApiLayer.addPerson(p);

        Intent intent = new Intent();
        intent.putExtra("newPerson", (Serializable) p);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}