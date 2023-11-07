package com.example.projectapp.programming_language;

import androidx.annotation.NonNull;

import com.example.projectapp.haircolor.Haircolor;

import java.io.Serializable;
import java.util.Objects;

public class ProgrammingLanguage implements Serializable {
    int id;
    String name;

    public ProgrammingLanguage(String name) {
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


