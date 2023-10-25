package com.example.second_act;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText txtToSecond;
    Button btnGoToSecond;
    TextView txtFromSecond;

    ActivityResultLauncher<Intent> secondActivityLauncher;
    public static final String TEXT_FROM_MAIN = "TextFromMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtToSecond = findViewById(R.id.txtToSecond);
        btnGoToSecond = findViewById(R.id.btnToSecond);
        txtFromSecond = findViewById(R.id.txtFromSecond);
        btnGoToSecond.setOnClickListener(this);

        secondActivityLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        Log.d("Result", String.valueOf(result.getResultCode()));
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            String msg = data.getStringExtra("Result");
                            txtFromSecond.setText(msg);
                        }
                        else {
                            txtFromSecond.setText("Cancelled!");
                        }
                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        if (v == btnGoToSecond)
        {
            Intent intent = new Intent(this, SecondActivity.class);
            String str = txtToSecond.getText().toString();
            intent.putExtra(TEXT_FROM_MAIN, "This was from Main.");

            Person person = new Person();

            person.name = "Tester";

            // Sending objects:
            intent.putExtra("PersonObj", person);
//            startActivity(intent);
            secondActivityLauncher.launch(intent);
        }
    }
}