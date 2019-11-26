package com.example.simpleweathermvp.data.network

import com.example.simpleweathermvp.data.network.response.WeatherFiveDaysResponse
import com.example.simpleweathermvp.data.network.response.WeatherTodayResponse
import io.reactivex.Observable
import io.reactivex.Single

class WeatherClient(
        private val weatherApi: WeatherApi
) {

    companion object {
        private val KEY_API: String = "7406cfd8ff307c4e3e1e44161431effe"

    }

    fun getWeatherToday(lat: Double, lng: Double): Observable<WeatherTodayResponse> {

        return weatherApi.getWeather(lat, lng, KEY_API)
    }

    fun getWeatherFiveDays(lat: Double, lng: Double): Observable<WeatherFiveDaysResponse> {
        return weatherApi.getWeatherFiveDays(lat, lng, KEY_API)
    }
}