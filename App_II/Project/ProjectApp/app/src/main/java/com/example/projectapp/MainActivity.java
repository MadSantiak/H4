package com.example.projectapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IPersonService personService =
                ServiceBuilder.buildService(IPersonService.class);
        Call<Person> request = personService.getPersonById(1001);

        request.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Person person = response.body();
                // set text perhaps
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                // set something to t.getMessage()
            }
        });
    }
}