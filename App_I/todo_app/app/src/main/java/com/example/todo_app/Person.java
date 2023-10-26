package com.example.todo_app;

public class Person {
    int id;
    String name;
    int age;

    public Person () {};
    public Person (int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + ", " + age;
    }
}
