package com.example.projectapp.person;

import com.example.projectapp.haircolor.Haircolor;

import java.io.Serializable;

public class Person implements Serializable {
    int id;
    String name;
    String phone;
    String address;
    String note;
    boolean favorite = false;
    Haircolor haircolor;
    int programminglanguage_id;

    public Person() {};
    public Person(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
    public Person(String name, String phone, String address, String note, boolean favorite) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.favorite = favorite;
    }
    public Person(String name, String phone, String address, String note, boolean favorite, Haircolor haircolor) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.favorite = favorite;
        this.haircolor = haircolor;
    }
    public Person(String name, String phone, String address, String note, boolean favorite, Haircolor haircolor, int programminglanguage_id) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.favorite = favorite;
        this.haircolor = haircolor;
        this.programminglanguage_id = programminglanguage_id;
    }

    public int getProgramminglanguage_id() {
        return programminglanguage_id;
    }
    public void setProgramminglanguage_id(int programminglanguage_id) {
        this.programminglanguage_id = programminglanguage_id;
    }

    public Haircolor getHaircolor() {
        return haircolor;
    }

    public void setHaircolor(Haircolor haircolor) {
        this.haircolor = haircolor;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
    public boolean getFavorite() { return favorite; }
}
