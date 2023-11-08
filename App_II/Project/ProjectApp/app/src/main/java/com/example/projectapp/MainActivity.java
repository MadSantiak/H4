package com.example.projectapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.projectapp.controllers.ApiLayer;
import com.example.projectapp.haircolor.AddHaircolorActivity;
import com.example.projectapp.haircolor.Haircolor;
import com.example.projectapp.haircolor.HaircolorActivity;
import com.example.projectapp.person.AddPersonActivity;
import com.example.projectapp.person.Person;
import com.example.projectapp.person.PersonAdapter;
import com.example.projectapp.programming_language.AddProgrammingLanguageActivity;
import com.example.projectapp.programming_language.ProgrammingLanguage;
import com.example.projectapp.programming_language.ProgrammingLanguageActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.leinardi.android.speeddial.SpeedDialActionItem;
import com.leinardi.android.speeddial.SpeedDialView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SpeedDialView.OnActionSelectedListener {

    ListView listPpl;
    FloatingActionButton fabAddPerson;
    SpeedDialView spdMenu;

    Toolbar toolbarMenu;
    PersonAdapter personAdapter;
    List<Person> persons = new ArrayList<>();

    ActivityResultLauncher<Intent> addPersonActivityLauncher, editPersonActivityLauncher,
            addHaircolorActivityLauncher, addProgrammingLanguageActivityLauncher,
            viewHaircolorActivityLauncher, viewProgrammingLanguageActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listPpl = findViewById(R.id.listPpl);
        persons = ApiLayer.getAllPerson();
        /**
         * Instantiate SpeedDial menu, creating the Action items based on Items defined in fab_menu.xml
         */
        spdMenu = findViewById(R.id.spdMenu);
        spdMenu.addActionItem(new SpeedDialActionItem.Builder(R.id.itemAddPerson, R.drawable.account_plus)
                .create());
        spdMenu.addActionItem(new SpeedDialActionItem.Builder(R.id.itemAddHaircolor, R.drawable.long_wavy_hair_variant)
                .create());
        spdMenu.addActionItem(new SpeedDialActionItem.Builder(R.id.itemAddProgLang, R.drawable.coding)
                .create());
        spdMenu.setOnActionSelectedListener(this);

        /**
         * Instantiate toolbar
         */
        toolbarMenu = findViewById(R.id.toolbarMenu);
        setSupportActionBar(toolbarMenu);

        /**
         * Activity launchers so we can fetch/respond to data changes
         */
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

        viewHaircolorActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        persons.clear();
                        persons.addAll(ApiLayer.getAllPerson());
                        personAdapter.notifyDataSetChanged();
                    }
                }
        );
        viewProgrammingLanguageActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        persons.clear();
                        persons.addAll(ApiLayer.getAllPerson());
                        personAdapter.notifyDataSetChanged();
                    }
                }
        );
        /**
         * Note we pass the "editPerson.." activity launcher along to the PersonAdapter, so we can update the main view
         * when the Person object is updated.
         * In essence, we want the PersonAdapter to use an activity launcher known by the MainActivity, so we can respond to the result generally, and thus update the view accordingly.
         */
        personAdapter = new PersonAdapter(persons, this, editPersonActivityLauncher);

        listPpl.setAdapter(personAdapter);


    }

    /**
     * Launches [C]RUD functionality for relevant classes, allowing the creating of new a new
     * (1) Person
     * (2) Haircolor
     * (3) Programming Language.
     * @param actionItem the {@link SpeedDialActionItem} that was selected.
     * @return
     */
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


    /**
     * This creates the "hamburger menu" at the top right of the App, using the items defined in main_menu.xml as options
     * @param menu The options menu in which you place your items.
     *
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * Response to selection of main_menu item selection in hamburger menu,
     * launching the respective acitivies which store their individual custom adapter views
     * in order to facilitate the establishment of CRUD functionality.
     * Note, however, that only "Delete" functionality is added as of assignment hand-in, as proof-of-concept.
     * "Create" is handled via SpeedDial menu options further up.
     * @param item The menu item that was selected.
     *
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuHaircolor) {
            Intent intent = new Intent(this, HaircolorActivity.class);
            viewHaircolorActivityLauncher.launch(intent);
        }
        if (item.getItemId() == R.id.menuProgrammingLanguage) {
            Intent intent = new Intent(this, ProgrammingLanguageActivity.class);
            viewProgrammingLanguageActivityLauncher.launch(intent);
        }
        return false;
    }
}