package com.example.personcustomlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listPpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Person> people = new ArrayList<Person>();

        people.add(new Person("Mads", 36, R.drawable.p2));
        people.add(new Person("Simone", 32, R.drawable.p1));
        people.add(new Person("Karsten", 21, R.drawable.p3));
        people.add(new Person("Laust", 25, R.drawable.p4));
        people.add(new Person("Rita", 76, R.drawable.p5));
        people.add(new Person("John", 45, R.drawable.p6));

        PersonAdapter personAdapter = new PersonAdapter(people, this);

        listPpl = findViewById(R.id.listPpl);
        listPpl.setAdapter(personAdapter);
    }
}