package com.example.simpleweathermvp.model.network

import com.example.simpleweathermvp.model.network.response.CityResponse
import com.google.gson.Gson
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface GooglePlacesApi {


    @GET("/maps/api/place/autocomplete/json")
    fun getCities(
            @Query("input") cityQwery: String,
            @Query("types") types: String,
            @Query("language") language: String,
            @Query("key") key: String
    ): Observable<CityResponse>

    companion object {

        private val BASE_URL = "https://maps.googleapis.com"

        fun create(): GooglePlacesApi {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(Gson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit.create(GooglePlacesApi::class.java)
        }
    }
}