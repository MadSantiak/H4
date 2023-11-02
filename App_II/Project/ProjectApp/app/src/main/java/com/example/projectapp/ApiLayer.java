package com.example.projectapp;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import retrofit2.Call;

public class ApiLayer {
    public static Person getPersonById (int id)
    {
        FutureTask<Person> futureTask = new FutureTask<>(new Callable<Person>() {
            @Override
            public Person call() throws Exception {
                Person p = null;
                IPersonService serv =
                        ServiceBuilder.buildService(IPersonService.class);

                Call<Person> req = serv.getPersonById(id);
                try {
                    p = req.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
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
}
