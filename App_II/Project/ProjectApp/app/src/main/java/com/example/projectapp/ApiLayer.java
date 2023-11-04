package com.example.projectapp;

import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import retrofit2.Call;
import retrofit2.Response;

public class ApiLayer {

    public static Person getPersonById (int id)
    {
        FutureTask<Person> futureTask = new FutureTask<>(new Callable<Person>() {
            @Override
            public Person call() {
                Person p = null;
                IPersonService serv =
                        ServiceBuilder.buildService(IPersonService.class);

                Call<Person> req = serv.getPersonById(id);
                try {
                    p = req.execute().body();
                    Log.d("Person:", p.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("ApiLayer", "Failed to fetch data.");
                }
                return p;
            }
        });
        Thread t = new Thread(futureTask);
        t.start();
        Person person = null;
        try {
            person = futureTask.get();
        } catch (Exception e) {
            Log.d("Thread", e.getMessage());
        }
        Log.d("Perosn", person.toString());
        return person;
    }

    public static List<Person> getAllPerson() {
        Log.d("getAllPerson", "Start");
        FutureTask<List<Person>> futureTask = new FutureTask<>(new Callable<List<Person>>() {
            @Override
            public List<Person> call() throws Exception {
                Log.d("getAllPerson", "call");
                List<Person> people = null;
                IPersonService serv = ServiceBuilder.buildService(IPersonService.class);
                Call<List<Person>> request = serv.getAllPerson();
                Log.d("getAllPerson", "trying");
                try {
                    Log.d("getAllPerson", "getting people");
                    people = request.execute().body();
                    Log.d("getAllPerson", "got people");
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("getAllPerson", "Failed");
                }
                return people;
            }
        });
        Log.d("getAllPerson", "Thread");
        Thread t = new Thread(futureTask);
        t.start();
        List<Person> people = null;
        try {
            people = futureTask.get();
            Log.d("People get", people.toString());
        } catch (Exception e) {
            Log.d("Thread ahoy", e.getMessage());
        }
        return people;
    }
}
