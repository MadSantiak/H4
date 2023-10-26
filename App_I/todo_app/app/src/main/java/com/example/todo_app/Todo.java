package com.example.todo_app;

public class Todo {
    String description;
    Boolean isDone = false;
    Person person;

    public Todo() {};
    public Todo(Person person, String description) {
        this.person = person;
        this.description = description;

    }

    public Boolean getDone() {
        return isDone;
    }

    public Person getPerson() {
        return person;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Assignee: " + person.name + " Task: " + description;
    }
}
