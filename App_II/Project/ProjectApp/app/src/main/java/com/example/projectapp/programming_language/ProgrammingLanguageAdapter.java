package com.example.projectapp.programming_language;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectapp.R;
import com.example.projectapp.controllers.ApiLayer;
import com.example.projectapp.haircolor.Haircolor;
import com.example.projectapp.haircolor.HaircolorActivity;

import java.util.List;

public class ProgrammingLanguageAdapter extends BaseAdapter {
    private final List<ProgrammingLanguage> listPl;
    private ProgrammingLanguageActivity mainPl;

    TextView txtPlName;
    Button btnPlDelete;

    public ProgrammingLanguageAdapter(List<ProgrammingLanguage> listPl, ProgrammingLanguageActivity mainPl) {
        this.listPl = listPl;
        this.mainPl = mainPl;
    }

    @Override
    public int getCount() {
        return listPl.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mainPl);
        View v = inflater.inflate(R.layout.custom_programminglanguage,null);


        ProgrammingLanguage pl = listPl.get(position);

        txtPlName = v.findViewById(R.id.txtPlName);
        txtPlName.setText(pl.toString());
        btnPlDelete = v.findViewById(R.id.btnPlDelete);

        btnPlDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(mainPl);
                alert.setTitle("Delete Programming Language?");
                alert.setMessage("Are you sure you wish to delete this programming language?");

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int removeAt = position;
                        listPl.remove(removeAt);
                        ApiLayer.delProgrammingLanguage(pl.getId());
                        notifyDataSetChanged();
                    }
                });

                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();
            }
        });

        return v;
    }
}
