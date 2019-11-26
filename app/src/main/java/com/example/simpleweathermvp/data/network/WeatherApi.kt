package com.example.simpleweathermvp.data.network

import com.example.simpleweathermvp.data.network.response.WeatherFiveDaysResponse
import com.example.simpleweathermvp.data.network.response.WeatherTodayResponse
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    companion object {

        private val BASE_URL = "http://api.openweathermap.org"

        fun create(): WeatherApi {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(Gson()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()

            return retrofit.create(WeatherApi::class.java)
        }
    }

    @GET("/data/2.5/weather")
    fun getWeather(
            @Query("lat") lat: Double,
            @Query("lon") lon: Double,
            @Query("APPID") key: String
    ): Observable<WeatherTodayResponse>

    @GET("/data/2.5/forecast")
    fun getWeatherFiveDays(
            @Query("lat") lat: Double,
            @Query("lon") lon: Double,
            @Query("APPID") key: String
    ): Observable<WeatherFiveDaysResponse>
}