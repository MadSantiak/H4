package com.example.todo_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Person[] people = {
            new Person(36, "Mads"),
            new Person(32, "Simone")
    };

    private ArrayList<Todo> todoArrayList = new ArrayList<>();

    ListView todoList;
    Spinner pplSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Populate data
        todoArrayList.add(new Todo(people[0], "Program and worry, in no particular order."));
        todoArrayList.add(new Todo(people[1], "Be your awesome self."));

        // Get views
        pplSpinner = findViewById(R.id.pplSpinner);
        todoList = findViewById(R.id.todoList);

        ArrayAdapter<Person> pplAdapter = new ArrayAdapter<Person>(this, android.R.layout.simple_spinner_item, people);
        pplAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pplSpinner.setAdapter(pplAdapter);

        ArrayAdapter<Todo> todoAdapter = new TodoAdapter(this, 0, todoArrayList);

        todoList.setAdapter(todoAdapter);
    }
}