package com.example.projectapp.haircolor;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectapp.MainActivity;
import com.example.projectapp.R;
import com.example.projectapp.controllers.ApiLayer;
import com.example.projectapp.person.Person;
import com.example.projectapp.person.PersonAdapter;

import java.util.List;

public class HaircolorAdapter extends BaseAdapter {
    private final List<Haircolor> listHc;
    private HaircolorActivity mainHc;

    TextView txtHcName;
    Button btnHcDelete;

    public HaircolorAdapter(List<Haircolor> listHc, HaircolorActivity mainHc) {
        this.listHc = listHc;
        this.mainHc = mainHc;
    }

    @Override
    public int getCount() {
        return listHc.size();
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
        LayoutInflater inflater = LayoutInflater.from(mainHc);
        View v = inflater.inflate(R.layout.custom_haircolor,null);


        Haircolor hc = listHc.get(position);

        txtHcName = v.findViewById(R.id.txtHcName);
        txtHcName.setText(hc.toString());
        btnHcDelete = v.findViewById(R.id.btnHcDelete);

        btnHcDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(mainHc);
                alert.setTitle("Delete haircolor?");
                alert.setMessage("Are you sure you wish to delete this haircolor?");

                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int removeAt = position;
                        listHc.remove(removeAt);
                        ApiLayer.delHaircolor(hc.getId());
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
