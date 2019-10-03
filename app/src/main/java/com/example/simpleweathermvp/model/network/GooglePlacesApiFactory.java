package com.example.simpleweathermvp.model.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GooglePlacesApiFactory {

    static final String BASE_URL = "https://maps.googleapis.com";


    public static GooglePlacesApi factory(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        GooglePlacesApi googlePlacesApi = retrofit.create(GooglePlacesApi.class);

        return googlePlacesApi;
    }
}
