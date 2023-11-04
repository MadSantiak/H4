package com.example.projectapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    private static final String URL = "http://192.168.1.145:8080/ProjectWebAPI/api/";

    private static Retrofit retrofit =
            new Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

    public static <P> P buildService(Class<P> serviceType)
    {
        return retrofit.create(serviceType);
    }
}
