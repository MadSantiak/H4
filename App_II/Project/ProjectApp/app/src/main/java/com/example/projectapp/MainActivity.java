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

import com.example.projectapp.controllers.ApiLayer;
import com.example.projectapp.person.AddPersonActivity;
import com.example.projectapp.person.Person;
import com.example.projectapp.person.PersonAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listPpl;
    FloatingActionButton fabAddPerson;
    PersonAdapter personAdapter;
    List<Person> persons = new ArrayList<>();

    ActivityResultLauncher<Intent> addPersonActivityLauncher, editPersonActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listPpl = findViewById(R.id.listPpl);
        persons = ApiLayer.getAllPerson();

        fabAddPerson = findViewById(R.id.fabAddPerson);
        fabAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddPersonActivity.class);

                addPersonActivityLauncher.launch(intent);
            }
        });

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
        personAdapter = new PersonAdapter(persons, this, editPersonActivityLauncher);

        listPpl.setAdapter(personAdapter);

    }
}