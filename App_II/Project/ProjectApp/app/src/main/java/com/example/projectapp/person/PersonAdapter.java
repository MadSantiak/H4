package com.example.projectapp.person;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;

import com.example.projectapp.controllers.ApiLayer;
import com.example.projectapp.MainActivity;
import com.example.projectapp.R;
import com.example.projectapp.haircolor.Haircolor;
import com.example.projectapp.programming_language.ProgrammingLanguage;

import java.util.List;

public class PersonAdapter extends BaseAdapter {

    int number;
    private final List<Person> listPpl;
    private MainActivity main;

    TextView txtName,txtPhone,txtAddress,txtNote,txtHaircolor,txtProgLang;
    CheckBox isFavorite; // Deprecated!

    ImageView imgFav;

    Button btnDel, btnEdit;

    ActivityResultLauncher<Intent> editPersonActivityLauncher;

    public PersonAdapter(List<Person> listPpl, MainActivity main)
    {
        this.listPpl = listPpl;
        this.main = main;
    }

    public PersonAdapter(List<Person> listPpl, MainActivity main, ActivityResultLauncher editPersonActivityLauncher)
    {
        this.listPpl = listPpl;
        this.main = main;
        this.editPersonActivityLauncher = editPersonActivityLauncher;
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

        txtName = v.findViewById(R.id.txtName);
        txtName.setText(person.getName());
        txtAddress = v.findViewById(R.id.txtAddress);
        txtAddress.setText(person.getAddress());
        txtNote = v.findViewById(R.id.txtNote);
        txtNote.setText(person.getNote());
        txtPhone = v.findViewById(R.id.txtPhone);
        txtPhone.setText(person.getPhone());

        /**
         * Sets Haircolor and Programming Language respectively.
         * Checks if value is null to avoid null-reference erros.
         */
        txtHaircolor = v.findViewById(R.id.txtHaircolor);
        Haircolor hc = person.getHaircolor();
        if (hc != null) txtHaircolor.setText(hc.toString());

        txtProgLang = v.findViewById(R.id.txtProgLang);
        ProgrammingLanguage pl = person.getProgramminglanguage();
        if (pl != null) txtProgLang.setText(pl.toString());

        imgFav = v.findViewById(R.id.imgFav);

        setInternalFav(person);

        /**
         * Switches Favorite status on click on ImageView for the person object in question. Then calls helper to switch images.
         */
        imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                person.setFavorite(!person.getFavorite());
                setInternalFav(person);

            }
        });


        btnDel = v.findViewById(R.id.btnDel);
        btnEdit = v.findViewById(R.id.btnEdit);

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
                        listPpl.remove(removeAt);
                        ApiLayer.delPerson(person.getId());
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
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(main, EditPersonActivity.class);
                editIntent.putExtra("person", person);
                editPersonActivityLauncher.launch(editIntent);
            }
        });



        return v;
    }

    /**
     * Helper function for switching "Favorite" picture based on whether isFavorite is true or not.
     * @param person
     */
    private void setInternalFav(Person person) {
        if (person.getFavorite()) {
            imgFav.setImageResource(R.drawable.star_fav);
        } else {
            imgFav.setImageResource(R.drawable.star_unfav);
        }
        ApiLayer.updatePerson(person);
        notifyDataSetChanged();
    }
}