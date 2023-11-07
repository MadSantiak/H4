package com.example.projectapp.haircolor;

import com.example.projectapp.person.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IHaircolorService {
    @GET("Haircolor")
    Call<List<Haircolor>> getAllHaircolor();

    @GET("Haircolor/{id}")
    Call<Haircolor> getHaircolorById(@Path("id") int id);

    @POST("Haircolor")
    Call<Integer> addHaircolor(@Body Haircolor hc);

    @DELETE("Haircolor/{id}")
    Call<Void> delHaircolor(@Path("id") int id);

}
