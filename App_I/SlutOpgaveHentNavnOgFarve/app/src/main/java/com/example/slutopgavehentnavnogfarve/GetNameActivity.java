package com.example.slutopgavehentnavnogfarve;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class GetNameActivity extends AppCompatActivity implements View.OnClickListener {

    RadioGroup radGroup;
    TextView nameLabel;
    EditText nameField;
    Button btnSendName;

    Intent mainIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_name);

        radGroup = findViewById(R.id.radioGrp);
        nameLabel = findViewById(R.id.nameLabel);
        nameField = findViewById(R.id.nameField);
        btnSendName = findViewById(R.id.btnSendName);

        radGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radBtn = radioGroup.findViewById(i);
                String relType = radBtn.getText().toString();
                nameLabel.setText(relType + "'s name:");
            }
        });

        btnSendName.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnSendName)
        {
            String relType = nameLabel.getText().toString();
            String relName = nameField.getText().toString();

            Log.d("Type", relType);
            Log.d("Name", relName);
            mainIntent.putExtra("nameLabel", relType);
            mainIntent.putExtra("nameField", relName);
            setResult(Activity.RESULT_OK, mainIntent);
            finish();
        }
    }
}