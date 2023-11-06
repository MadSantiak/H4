package com.example.projectapp.programming_language;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IProgrammingLanguageService {
    @GET("ProgrammingLanguage")
    Call<List<ProgrammingLanguage>> getAllProgrammingLanguage();

    @GET("ProgrammingLanguage/{id}")
    Call<ProgrammingLanguage> getProgrammingLanguageById(@Path("id") int id);

}
