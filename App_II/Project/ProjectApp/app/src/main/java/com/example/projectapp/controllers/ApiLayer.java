package com.example.projectapp.controllers;

import android.util.Log;

import com.example.projectapp.haircolor.Haircolor;
import com.example.projectapp.haircolor.IHaircolorService;
import com.example.projectapp.person.IPersonService;
import com.example.projectapp.person.Person;
import com.example.projectapp.programming_language.IProgrammingLanguageService;
import com.example.projectapp.programming_language.ProgrammingLanguage;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import retrofit2.Call;
import retrofit2.Response;

public class ApiLayer {

    /**
     * Generalized Comment:
     * getXByID:
     *      Fetches X object from Database using IXService, which in turn uses ApiLayer, sending variable (int) along in order to find the correct dataset.
     * getAllX:
     *      Fetches all X objects from database tabel, returns List of X objects.
     * addX:
     *      Adds a Person object and returns the ID of the created object
     * delX:
     *      Deletes a given recordset based on the ID sent along as an argument.
     * updateX:
     *      Updates the X object sent along as an argument.
     */
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
        } catch (Exception e) {
        }
        return people;
    }

    public static Integer addPerson(Person person)
    {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Integer i = null;
                IPersonService serv = ServiceBuilder.buildService(IPersonService.class);

                Call<Integer> req = serv.addPerson(person);
                try {
                    i = req.execute().body();
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
    public static Integer addHaircolor(Haircolor hc)
    {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Integer i = null;
                IHaircolorService serv = ServiceBuilder.buildService(IHaircolorService.class);

                Call<Integer> req = serv.addHaircolor(hc);
                try {
                    i = req.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return i;
            }
        });
        Thread t = new Thread(futureTask);
        t.start();
        Integer hcId = null;
        try {
            hcId = futureTask.get();
        } catch (Exception e) {
            Log.e("Thread error:", e.getMessage());
        }
        return hcId;
    }
    public static void delHaircolor(int id)
    {
        FutureTask<Void> futureTask = new FutureTask<>(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                IHaircolorService serv = ServiceBuilder.buildService(IHaircolorService.class);

                Call<Void> req = serv.delHaircolor(id);
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
    public static List<ProgrammingLanguage> getAllProgrammingLanguage() {
        FutureTask<List<ProgrammingLanguage>> futureTask = new FutureTask<>(new Callable<List<ProgrammingLanguage>>() {
            @Override
            public List<ProgrammingLanguage> call() throws Exception {
                List<ProgrammingLanguage> programmingLanguages = null;
                IProgrammingLanguageService serv = ServiceBuilder.buildService(IProgrammingLanguageService.class);
                Call<List<ProgrammingLanguage>> request = serv.getAllProgrammingLanguage();
                try {
                    programmingLanguages = request.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return programmingLanguages;
            }
        });
        Thread t = new Thread(futureTask);
        t.start();
        List<ProgrammingLanguage> programmingLanguages = null;
        try {
            programmingLanguages = futureTask.get();
        } catch (Exception e) {
        }
        return programmingLanguages;
    }

    public static ProgrammingLanguage getProgrammingLanguageById (int id)
    {
        FutureTask<ProgrammingLanguage> futureTask = new FutureTask<>(new Callable<ProgrammingLanguage>() {
            @Override
            public ProgrammingLanguage call() {
                ProgrammingLanguage programmingLanguage = null;
                IProgrammingLanguageService serv =
                        ServiceBuilder.buildService(IProgrammingLanguageService.class);

                Call<ProgrammingLanguage> req = serv.getProgrammingLanguageById(id);
                try {
                    programmingLanguage = req.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.d("ApiLayer", "Failed to fetch data.");
                }
                return programmingLanguage;
            }
        });
        Thread t = new Thread(futureTask);
        t.start();
        ProgrammingLanguage prglng = null;
        try {
            prglng = futureTask.get();
        } catch (Exception e) {
            Log.d("Thread", e.getMessage());
        }
        return prglng;
    }
    public static Integer addProgrammingLanguage(ProgrammingLanguage pl)
    {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Integer i = null;
                IProgrammingLanguageService serv = ServiceBuilder.buildService(IProgrammingLanguageService.class);

                Call<Integer> req = serv.addProgrammingLanguage(pl);
                try {
                    i = req.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return i;
            }
        });
        Thread t = new Thread(futureTask);
        t.start();
        Integer plId = null;
        try {
            plId = futureTask.get();
        } catch (Exception e) {
            Log.e("Thread error:", e.getMessage());
        }
        return plId;
    }
    public static void delProgrammingLanguage(int id)
    {
        FutureTask<Void> futureTask = new FutureTask<>(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                IProgrammingLanguageService serv = ServiceBuilder.buildService(IProgrammingLanguageService.class);

                Call<Void> req = serv.delProgrammingLanguage(id);
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
