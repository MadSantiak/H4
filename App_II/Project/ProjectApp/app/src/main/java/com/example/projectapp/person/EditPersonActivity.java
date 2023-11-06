package com.example.projectapp.person;

import static com.example.projectapp.controllers.ApiLayer.getHaircolorById;

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

import com.example.projectapp.controllers.ApiLayer;
import com.example.projectapp.R;
import com.example.projectapp.haircolor.Haircolor;

import java.io.Serializable;
import java.util.List;

public class EditPersonActivity extends AppCompatActivity {

    EditText txtName, txtPhone, txtAddress, txtNote;
    CheckBox isFavorite;
    Spinner spnHaircolor;
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
        spnHaircolor = findViewById(R.id.spnHaircolor);

        List<Haircolor> haircolors = ApiLayer.getAllHaircolor();
        ArrayAdapter<Haircolor> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, haircolors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnHaircolor.setAdapter(adapter);

        btnUpdate = findViewById(R.id.btnUpdate);

        personIntent = getIntent();


        Person person = (Person) personIntent.getSerializableExtra("person");
        if (person != null) {
            txtName.setText(person.getName());
            txtPhone.setText(person.getPhone());
            txtAddress.setText(person.getAddress());
            txtNote.setText(person.getNote());
            isFavorite.setChecked(person.getFavorite());

            // Bit messier than overwriting equals() on the class, but simpler:
            // Iterate through each option in Spinner, and compare the ID with the id of object sent
            // along to the activity (person).
            int hcId = person.getHaircolor_id();
            for (int i = 0; i < haircolors.size(); i++){
                if (haircolors.get(i).getId() == hcId)
                {
                    spnHaircolor.setSelection(i);
                }
            }

        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.setName(txtName.getText().toString());
                person.setAddress(txtAddress.getText().toString());
                person.setPhone(txtPhone.getText().toString());
                person.setNote(txtNote.getText().toString());
                person.setFavorite(isFavorite.isChecked());
                Haircolor hc = (Haircolor) spnHaircolor.getSelectedItem();
                person.setHaircolor_id(hc.getId());

                ApiLayer.updatePerson(person);

                Intent intent = new Intent();
                intent.putExtra("updPerson", (Serializable) person);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}