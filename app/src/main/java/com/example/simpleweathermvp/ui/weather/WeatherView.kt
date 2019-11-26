package com.example.simpleweathermvp.ui.weather

import com.example.simpleweathermvp.domain.entity.Weather
import com.example.simpleweathermvp.ui.entity.WeatherUi

interface WeatherView {

    fun showWeather(weather: List<WeatherUi>)
    //fun showProgressBar()
    //fun showProgressBarAndWeather()
}