package com.example.calc_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtResult;
    EditText var1,var2;
    Button btnCalc;
    ImageView imgOps;
    String strOperator;
    float fResult;
    float x = 0;
    float y = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        var1 = findViewById(R.id.var1);
        var2 = findViewById(R.id.var2);
        btnCalc = findViewById(R.id.btnCalc);
        imgOps = findViewById(R.id.imgOps);
        txtResult = findViewById(R.id.txtResult);

        registerForContextMenu(imgOps);
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x = Float.parseFloat(var1.getText().toString());
                y = Float.parseFloat(var2.getText().toString());
                switch (strOperator)
                {
                    case "+":
                        fResult =  x+y;
                        break;
                    case "-":
                        fResult = x-y;
                        break;
                    case "*":
                        fResult = x*y;
                        break;
                    case "/":
                        y = (y == 0) ? 1 : y;
                        fResult = x/y;
                        break;
                    default:
                        fResult = 0;
                }
                txtResult.setText(String.valueOf(fResult));

            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v == imgOps)
        {
            menu.setHeaderTitle("Choose operator:");
            menu.add(Menu.NONE, 1, Menu.NONE, "+");
            menu.add(Menu.NONE, 2, Menu.NONE, "-");
            menu.add(Menu.NONE, 3, Menu.NONE, "*");
            menu.add(Menu.NONE, 4, Menu.NONE, "/");
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        strOperator = item.getTitle().toString();
        return super.onContextItemSelected(item);
    }
}