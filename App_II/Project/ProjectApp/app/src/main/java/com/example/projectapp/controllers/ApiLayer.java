package com.example.projectapp.controllers;

import android.util.Log;

import com.example.projectapp.haircolor.Haircolor;
import com.example.projectapp.haircolor.IHaircolorService;
import com.example.projectapp.person.IPersonService;
import com.example.projectapp.person.Person;

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

    public static Integer addPerson(Person person)
    {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Log.d("TEST", "TEST");
                Integer i = null;
                IPersonService serv = ServiceBuilder.buildService(IPersonService.class);

                Call<Integer> req = serv.addPerson(person);
                try {
                    i = req.execute().body();
                    Log.d("BODY", i.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return i;
            }
        });
    Thread t = new Thread(futureTask);
    t.start();
    Integer pId = null;
    try {
        pId = futureTask.get();
    } catch (Exception e) {
        Log.e("Thread error:", e.getMessage());
        }
    return pId;
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

    public static List<Haircolor> getAllHaircolor() {
        FutureTask<List<Haircolor>> futureTask = new FutureTask<>(new Callable<List<Haircolor>>() {
            @Override
            public List<Haircolor> call() throws Exception {
                List<Haircolor> haircolors = null;
                IHaircolorService serv = ServiceBuilder.buildService(IHaircolorService.class);
                Call<List<Haircolor>> request = serv.getAllHaircolor();
                try {
                    haircolors = request.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return haircolors;
            }
        });
        Thread t = new Thread(futureTask);
        t.start();
        List<Haircolor> haircolors = null;
        try {
            haircolors = futureTask.get();
        } catch (Exception e) {
        }
        return haircolors;
    }

    public static Haircolor getHaircolorById (int id)
    {
        FutureTask<Haircolor> futureTask = new FutureTask<>(new Callable<Haircolor>() {
            @Override
            public Haircolor call() {
                Haircolor hc = null;
                IHaircolorService serv =
                        ServiceBuilder.buildService(IHaircolorService.class);

                Call<Haircolor> req = serv.getHaircolorById(id);
                try {
                    hc = req.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("ApiLayer", "Failed to fetch data.");
                }
                return hc;
            }
        });
        Thread t = new Thread(futureTask);
        t.start();
        Haircolor haircolor = null;
        try {
            haircolor = futureTask.get();
        } catch (Exception e) {
            Log.d("Thread", e.getMessage());
        }
        return haircolor;
    }

}
