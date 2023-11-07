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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.projectapp.controllers.ApiLayer;
import com.example.projectapp.R;
import com.example.projectapp.haircolor.Haircolor;
import com.example.projectapp.programming_language.ProgrammingLanguage;

import java.io.Serializable;
import java.util.List;

public class EditPersonActivity extends AppCompatActivity {

    EditText txtName, txtPhone, txtAddress, txtNote;
    CheckBox isFavorite;
    Spinner spnHaircolor;
    RadioGroup radPrg;
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

        // Spinner setup
        spnHaircolor = findViewById(R.id.spnHaircolor);
        List<Haircolor> haircolors = ApiLayer.getAllHaircolor();
        ArrayAdapter<Haircolor> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, haircolors);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnHaircolor.setAdapter(adapter);

        // RadioButton setup
        List<ProgrammingLanguage> prgLangs = ApiLayer.getAllProgrammingLanguage();
        radPrg = findViewById(R.id.radPrg);
        for (int i = 0; i < prgLangs.size(); i++) {
            RadioButton option = new RadioButton(this);
            option.setId(i+1);
            option.setText(prgLangs.get(i).getName());
            radPrg.addView(option);
        }

        btnUpdate = findViewById(R.id.btnUpdate);

        personIntent = getIntent();


        Person person = (Person) personIntent.getSerializableExtra("person");
        if (person != null) {
            txtName.setText(person.getName());
            txtPhone.setText(person.getPhone());
            txtAddress.setText(person.getAddress());
            txtNote.setText(person.getNote());
            isFavorite.setChecked(person.getFavorite());

            Haircolor hc = person.getHaircolor();
            if (hc != null) {
                for (int i = 0; i < haircolors.size(); i++) {
                    if (haircolors.get(i).getId() == hc.getId()) {
                        spnHaircolor.setSelection(i);
                    }
                }
            }
            ProgrammingLanguage pl = person.getProgramminglanguage();
            if (pl != null) {
                for (int i = 0; i < prgLangs.size(); i++) {
                    if (prgLangs.get(i).getId() == pl.getId()) {
                        radPrg.check(radPrg.getChildAt(i).getId());
                    }
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
                person.setHaircolor(hc);

                Log.d("Checked button", String.valueOf(radPrg.getCheckedRadioButtonId()));
                ProgrammingLanguage pl = (ProgrammingLanguage) ApiLayer.getProgrammingLanguageById(radPrg.getCheckedRadioButtonId());
                Log.d("PL", pl.toString());
                person.setProgramminglanguage(pl);

                ApiLayer.updatePerson(person);

                Intent intent = new Intent();
                intent.putExtra("updPerson", (Serializable) person);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}