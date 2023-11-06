package com.example.projectapp.person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IPersonService {
    @GET("Person")
    Call<List<Person>> getAllPerson();

    @GET("Person/{id}")
    Call<Person> getPersonById(@Path("id") int id);

    @POST("Person")
    Call<Integer> addPerson(@Body Person person);

    @DELETE("Person/{id}")
    Call<Void> delPerson(@Path("id") int id);

    @PUT("Person/{id}")
    Call<Void> updatePerson(@Body Person person);
}
