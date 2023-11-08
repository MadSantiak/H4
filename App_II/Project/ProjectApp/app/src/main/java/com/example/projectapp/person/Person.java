package com.example.projectapp.person;

import android.util.Log;

import com.example.projectapp.haircolor.Haircolor;
import com.example.projectapp.programming_language.ProgrammingLanguage;

import java.io.Serializable;

public class Person implements Serializable {
    int id;
    String name;
    String phone;
    String address;
    String note;
    boolean favorite = false;
    Haircolor haircolor;
    ProgrammingLanguage programminglanguage;

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

    /**
     * Final Constructor; note for the sake of historics, the previous constructors made relevant throughout development
     * have been kept intact. This also to (theoretically) avoid errors caused by inconsistencies between parameters and arguments on potentially antiquated
     * object instantiations.
     * @param name
     * @param phone
     * @param address
     * @param note
     * @param favorite
     * @param haircolor
     * @param programminglanguage
     */
    public Person(String name, String phone, String address, String note, boolean favorite, Haircolor haircolor, ProgrammingLanguage programminglanguage) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.note = note;
        this.favorite = favorite;
        this.haircolor = haircolor;
        this.programminglanguage = programminglanguage;
    }

    public ProgrammingLanguage getProgramminglanguage() {
        return programminglanguage;
    }
    public void setProgramminglanguage(ProgrammingLanguage programminglanguage) {
        this.programminglanguage = programminglanguage;
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
