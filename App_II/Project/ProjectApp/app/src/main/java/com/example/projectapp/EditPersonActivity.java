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

public class EditPersonActivity extends AppCompatActivity {

    EditText txtName, txtPhone, txtAddress, txtNote;
    CheckBox isFavorite;
    Button btnUpdate;

    Intent personIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);

        txtName = findViewById(R.id.txtName);
        txtPhone = findViewById(R.id.txtPhone);
        txtAddress = findViewById(R.id.txtAddress);
        txtNote = findViewById(R.id.txtNote);
        isFavorite = findViewById(R.id.isFavorite);
        btnUpdate = findViewById(R.id.btnUpdate);

        personIntent = getIntent();

        Person person = (Person) personIntent.getSerializableExtra("person");
        if (person != null) {
            txtName.setText(person.getName());
            txtPhone.setText(person.getPhone());
            txtAddress.setText(person.getAddress());
            txtNote.setText(person.getNote());
            isFavorite.setChecked(person.getFavorite());
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.setName(txtName.getText().toString());
                person.setAddress(txtAddress.getText().toString());
                person.setPhone(txtPhone.getText().toString());
                person.setNote(txtNote.getText().toString());
                person.setFavorite(isFavorite.isChecked());
                ApiLayer.updatePerson(person);

                Intent intent = new Intent();
                intent.putExtra("updPerson", (Serializable) person);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}