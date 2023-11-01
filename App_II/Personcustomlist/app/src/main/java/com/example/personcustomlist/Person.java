package com.example.personcustomlist;

public class Person {

    String name;
    int age;
    int picNo;

    public Person(String name, int age, int picNo) {
        this.name = name;
        this.age = age;
        this.picNo = picNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPicNo() {
        return picNo;
    }

    public void setPicNo(int picNo) {
        this.picNo = picNo;
    }
}
