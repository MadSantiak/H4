package com.example.personcustomlist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends BaseAdapter {

    int number;
    private final List<Person> listPpl;
    private MainActivity main;

    public PersonAdapter(List<Person> listPpl, MainActivity main)
    {
        this.listPpl = listPpl;
        this.main = main;
    }

    @Override
    public int getCount() {
        return listPpl.size();
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
        LayoutInflater inflater = LayoutInflater.from(main);
        View v = inflater.inflate(R.layout.custom_person,null);

        if (position % 2 == 0)
        {
            v.setBackgroundResource(R.color.light_grey);
        }
        else {
            v.setBackgroundResource(R.color.dark_grey);
        }

        Person person = listPpl.get(position);

        ImageView imgPerson = v.findViewById(R.id.imgPerson);
        imgPerson.setImageResource(person.getPicNo());

        TextView txtName = v.findViewById(R.id.txtName);
        txtName.setText(person.getName());

        TextView txtAge = v.findViewById(R.id.txtAge);
        txtAge.setText(String.valueOf(person.getAge()));
        // Hack:
        txtAge.setText(person.getAge() + ""); // This converts the int to a string.

        Button btnDel = v.findViewById(R.id.btnDel);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(main);
                alert.setTitle("Delete person?");
                alert.setMessage("Are you sure you wish to delete this person?");

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int removeAt = position;
                        Person delPerson = listPpl.remove(removeAt);
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
