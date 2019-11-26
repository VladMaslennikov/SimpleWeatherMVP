package com.example.simpleweathermvp.domain.weather

import com.example.simpleweathermvp.data.geteway.WeatherGeteway
import com.example.simpleweathermvp.domain.entity.City

class WeatherByCityRefreshInteractor(private val weatherGeteway: WeatherGeteway) {

    fun getWeather(city: City){
        weatherGeteway.refreshWeatherByCity(city)
    }
}