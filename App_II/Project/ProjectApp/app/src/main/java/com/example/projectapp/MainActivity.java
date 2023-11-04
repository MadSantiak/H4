package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView listPpl;
    FloatingActionButton fabAddPerson;
    PersonAdapter personAdapter;
    List<Person> persons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listPpl = findViewById(R.id.listPpl);
        persons = ApiLayer.getAllPerson();
        Log.d("PERSONS", persons.toString());
        personAdapter = new PersonAdapter(persons, this);

        fabAddPerson = findViewById(R.id.fabAddPerson);

        listPpl.setAdapter(personAdapter);

    }
}