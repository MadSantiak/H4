package com.example.projectapp;

import android.app.Service;
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
        return person;
    }

    public static List<Person> getAllPerson() {
        FutureTask<List<Person>> futureTask = new FutureTask<>(new Callable<List<Person>>() {
            @Override
            public List<Person> call() throws Exception {
                List<Person> people = null;
                IPersonService serv = ServiceBuilder.buildService(IPersonService.class);
                Call<List<Person>> request = serv.getAllPerson();
                try {
                    people = request.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return people;
            }
        });
        Thread t = new Thread(futureTask);
        t.start();
        List<Person> people = null;
        try {
            people = futureTask.get();
            Log.d("People get", people.toString());
        } catch (Exception e) {
        }
        return people;
    }

    public static void addPerson(Person person)
    {
        FutureTask<Void> futureTask = new FutureTask<>(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                IPersonService serv = ServiceBuilder.buildService(IPersonService.class);

                Call<Void> req = serv.addPerson(person);
                try {
                    Response<Void> response = req.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return null;
            }
        });
    Thread t = new Thread(futureTask);
    t.start();

    try {
        futureTask.get();
    } catch (Exception e) {
        Log.e("Thread error:", e.getMessage());
        }
    }

    public static void delPerson(int id)
    {
        FutureTask<Void> futureTask = new FutureTask<>(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                IPersonService serv = ServiceBuilder.buildService(IPersonService.class);

                Call<Void> req = serv.delPerson(id);
                try {
                    Response<Void> response = req.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
        Thread t = new Thread(futureTask);
        t.start();

        try {
            futureTask.get();
        } catch (Exception e) {
            Log.e("Thread error:", e.getMessage());
        }
    }

    public static void updatePerson(Person person)
    {
        FutureTask<Void> futureTask = new FutureTask<>(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                IPersonService serv = ServiceBuilder.buildService(IPersonService.class);

                Call<Void> req = serv.updatePerson(person);
                try {
                    Response<Void> response = req.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
        Thread t = new Thread(futureTask);
        t.start();

        try {
            futureTask.get();
        } catch (Exception e) {
            Log.e("Thread error:", e.getMessage());
        }
    }


}
