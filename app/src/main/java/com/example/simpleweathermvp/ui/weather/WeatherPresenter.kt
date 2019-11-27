package com.example.simpleweathermvp.ui.weather

import android.annotation.SuppressLint
import com.example.simpleweathermvp.domain.entity.City
import com.example.simpleweathermvp.domain.weather.WeatherByCityRefreshInteractor
import com.example.simpleweathermvp.domain.weather.WeatherByCityChengesInteractor

class WeatherPresenter(private val weatherView: WeatherView,
                       private val weatherByCityRefreshInteractor: WeatherByCityRefreshInteractor,
                       private val weatherByCityChengesInteractor: WeatherByCityChengesInteractor) {



    fun weatherByCityRefresh(city: City){
        weatherByCityRefreshInteractor.getWeather(city)
    }

    @SuppressLint("CheckResult")
    fun weatherByCityChenges(city: City){

        weatherByCityChengesInteractor.execute(city)
                .subscribe({
                    weatherView.showWeather(it)
                })
    }
}