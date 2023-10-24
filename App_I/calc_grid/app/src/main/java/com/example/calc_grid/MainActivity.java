package com.example.calc_grid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView equationField;
    TextView resultField;
    String current_equation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        equationField = findViewById(R.id.text_equation);
        resultField = findViewById(R.id.text_result);
    }

    @Override
    public void onClick(View v) {
        current_equation = equationField.getText().toString();
        Button clicked_btn = (Button) v;
        current_equation += clicked_btn.getText().toString();
        Log.d("Equate:", current_equation);
        equationField.setText(current_equation);
    }

    public void onClickEqual(View v) {
        Expression calc = new ExpressionBuilder(current_equation).build();
        double res = calc.evaluate();
        Log.d("Result", String.valueOf(res));
        resultField.setText(String.valueOf(res));
    }

    public void onClickClear(View v) {
        resultField.setText("");
        equationField.setText("");
    }

}