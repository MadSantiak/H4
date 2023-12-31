package com.example.projectapp.haircolor;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

public class Haircolor implements Serializable {
    int id;
    String name;

    public Haircolor(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}


