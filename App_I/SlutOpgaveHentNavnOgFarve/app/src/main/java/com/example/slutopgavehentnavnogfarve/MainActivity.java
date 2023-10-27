package com.example.slutopgavehentnavnogfarve;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnName, btnColor;
    TextView nameRes;
    String relationType, relationName;
    String strRed, strGreen, strBlue;
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

                nameIntent.putExtra("name", relationName);
                nameIntent.putExtra("type", relationType);

                nameActivityLauncher.launch(nameIntent);
            }
        });

        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent colorIntent = new Intent(MainActivity.this, GetColorActivity.class);
                if (relationName != null)
                {
                    colorIntent.putExtra("relationType", relationType);
                }
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
                            relationType = res.getStringExtra("nameLabel");
                            relationName = res.getStringExtra("nameField");
                            String nameResult = relationType + "'s name: " + relationName;
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
                            strRed = res.getStringExtra("r");
                            strGreen = res.getStringExtra("g");
                            strBlue = res.getStringExtra("b");
                            String strColorCode = "#" + strRed + strGreen + strBlue;
                            Log.d("Red", strRed);
                            Log.d("Green", strGreen);
                            Log.d("Blue", strBlue);
                            Log.d("Code", strColorCode);
                            nameRes.setBackgroundColor(Color.parseColor(strColorCode));
                        }
                        else
                        {

                        }
                    }
                }
        );

    }
}