package com.example.todo_app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TodoAdapter extends ArrayAdapter<Todo> {
    private Context ctx;
    private List<Todo> todoList;

    public TodoAdapter(Context ctx, int resource, ArrayList<Todo> todos) {
        super(ctx, resource, todos);

        this.ctx = ctx;
        this.todoList = todos;
    }

    public View getView(int pos, View convertView, ViewGroup parent) {
        Todo todo = todoList.get(pos);

        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.todo_adapter_layout, null);

        if (pos % 2 == 0)
        {
            view.setBackgroundResource(R.color.grey);
        }
        else
        {
            view.setBackgroundResource(R.color.lightgrey);
        }

        TextView description = (TextView) view.findViewById(R.id.description);
        TextView person = (TextView) view.findViewById(R.id.person);
        CheckBox done = (CheckBox) view.findViewById(R.id.done);

        description.setText(String.valueOf(todo.getDescription()));
        person.setText(todo.getPerson().toString());
        done.setChecked(todo.getDone());

        return view;
    }
}
