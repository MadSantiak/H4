package com.example.slutopgavehentnavnogfarve;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnName, btnColor;
    TextView nameRes;

    ActivityResultLauncher<Intent> nameActivityLauncher, colorActivityLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnName = findViewById(R.id.btnName);
        btnColor = findViewById(R.id.btnColor);
        nameRes = findViewById(R.id.nameRes);

        btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nameIntent = new Intent(MainActivity.this, GetNameActivity.class);
                nameActivityLauncher.launch(nameIntent);
            }
        });

        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent colorIntent = new Intent(MainActivity.this, GetColorActivity.class);
                colorActivityLauncher.launch(colorIntent);
            }
        });

        nameActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == Activity.RESULT_OK)
                        {
                            Intent res = o.getData();
                            String nameLabel = res.getStringExtra("nameLabel");
                            String nameField = res.getStringExtra("nameField");
                            String nameResult = nameLabel + " " + nameField;
                            nameRes.setText(nameResult);
                        }
                        else
                        {

                        }
                    }
                }
        );
        colorActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == Activity.RESULT_OK)
                        {
                            Intent res = o.getData();
                        }
                        else
                        {

                        }
                    }
                }
        );

    }
}