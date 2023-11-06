package com.example.projectapp.haircolor;

import com.example.projectapp.person.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IHaircolorService {
    @GET("Haircolor")
    Call<List<Haircolor>> getAllHaircolor();

    @GET("Haircolor/{id}")
    Call<Haircolor> getHaircolorById(@Path("id") int id);

}
