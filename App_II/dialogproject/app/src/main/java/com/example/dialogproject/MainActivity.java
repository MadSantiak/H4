package com.example.dialogproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnShowDialogue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShowDialogue = findViewById(R.id.btnShowDialogue);
        btnShowDialogue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Pause");

        dialog.setMessage("Er der snart pause?");
        dialog.setPositiveButton("Ja", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Snackbar snack = Snackbar.make(btnShowDialogue, "Ok", Snackbar.LENGTH_LONG);
                snack.show();
            }
        });
        dialog.setNegativeButton("Nej", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Snackbar snack = Snackbar.make(
                        btnShowDialogue, "Nej, ikke endnu", Snackbar.LENGTH_LONG);
                snack.show();
            }
        });


        dialog.setNeutralButton("Dunno", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Snackbar snack = Snackbar.make(
                        btnShowDialogue, "Dunno", Snackbar.LENGTH_LONG).setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar sn = Snackbar
                                .make(btnShowDialogue, "JIT", Snackbar.LENGTH_LONG);
                    }
                });
                snack.show();
            }
        });

        dialog.show();
    }
}