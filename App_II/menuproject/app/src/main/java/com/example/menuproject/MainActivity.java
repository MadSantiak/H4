package com.example.menuproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menuPause:
                Toast.makeText(getApplicationContext(), "Pause!", Toast.LENGTH_LONG)
                        .show();
                return true;
            case R.id.menuIkkePause:
                Toast.makeText(getApplicationContext(), "Ikke Pause!", Toast.LENGTH_LONG)
                        .show();
                return true;
            case R.id.menuMåskePause:
                Toast.makeText(getApplicationContext(), "Måske Pause?", Toast.LENGTH_LONG)
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);


        }
    }
}