package com.example.simpleweathermvp.model.network;

import com.example.simpleweathermvp.model.network.response.CityResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GooglePlacesApi {

    @GET("/maps/api/place/autocomplete/json")
    Observable<CityResponse> getCities(
            @Query("input") String cityQwery,
            @Query("types") String types,
            @Query("language") String language,
            @Query("key") String key
    );

}
