package com.example.projectapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projectapp.controllers.ApiLayer;
import com.example.projectapp.haircolor.AddHaircolorActivity;
import com.example.projectapp.haircolor.Haircolor;
import com.example.projectapp.person.AddPersonActivity;
import com.example.projectapp.person.Person;
import com.example.projectapp.person.PersonAdapter;
import com.example.projectapp.programming_language.AddProgrammingLanguageActivity;
import com.example.projectapp.programming_language.ProgrammingLanguage;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SpeedDialView.OnActionSelectedListener {

    ListView listPpl;
    FloatingActionButton fabAddPerson;
    SpeedDialView spdMenu;
    PersonAdapter personAdapter;
    List<Person> persons = new ArrayList<>();

    ActivityResultLauncher<Intent> addPersonActivityLauncher, editPersonActivityLauncher, addHaircolorActivityLauncher, addProgrammingLanguageActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listPpl = findViewById(R.id.listPpl);
        persons = ApiLayer.getAllPerson();

        spdMenu = findViewById(R.id.spdMenu);
        spdMenu.addActionItem(new SpeedDialActionItem.Builder(R.id.itemAddPerson, R.drawable.account_plus)
                .create());
        spdMenu.addActionItem(new SpeedDialActionItem.Builder(R.id.itemAddHaircolor, R.drawable.long_wavy_hair_variant)
                .create());
        spdMenu.addActionItem(new SpeedDialActionItem.Builder(R.id.itemAddProgLang, R.drawable.coding)
                .create());
        spdMenu.setOnActionSelectedListener(this);


        addPersonActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == Activity.RESULT_OK)
                        {
                            Intent res = o.getData();
                            Person p = (Person) res.getSerializableExtra("newPerson");
                            persons.add(p);
                            personAdapter.notifyDataSetChanged();
                        }
                    }
                }
        );
        editPersonActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == Activity.RESULT_OK)
                        {
                            Intent upd = o.getData();
                            persons.clear();
                            persons.addAll(ApiLayer.getAllPerson());
                            personAdapter.notifyDataSetChanged();
                        }
                    }
                }
        );
        addHaircolorActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == Activity.RESULT_OK)
                        {
                            Intent res = o.getData();
                            Haircolor hc = (Haircolor) res.getSerializableExtra("newHaircolor");
                            Toast.makeText(MainActivity.this, "Created new Haircolor: " + hc.toString(), Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                }
        );
        addProgrammingLanguageActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == Activity.RESULT_OK)
                        {
                            Intent res = o.getData();
                            ProgrammingLanguage pl = (ProgrammingLanguage) res.getSerializableExtra("newProgrammingLanguage");
                            Toast.makeText(MainActivity.this, "Created new Programming Language: " + pl.toString(), Toast.LENGTH_LONG)
                                    .show();
                        }
                    }
                }
        );
        personAdapter = new PersonAdapter(persons, this, editPersonActivityLauncher);

        listPpl.setAdapter(personAdapter);

    }

    @Override
    public boolean onActionSelected(SpeedDialActionItem actionItem) {
        if (actionItem.getId() == R.id.itemAddPerson) {
            Intent intent = new Intent(MainActivity.this, AddPersonActivity.class);
            addPersonActivityLauncher.launch(intent);
        }
        if (actionItem.getId() == R.id.itemAddHaircolor) {
            Intent intent = new Intent(MainActivity.this, AddHaircolorActivity.class);
            addHaircolorActivityLauncher.launch(intent);
        }
        if (actionItem.getId() == R.id.itemAddProgLang) {
            Intent intent = new Intent(MainActivity.this, AddProgrammingLanguageActivity.class);
            addProgrammingLanguageActivityLauncher.launch(intent);
        }
        return true;
    }
}