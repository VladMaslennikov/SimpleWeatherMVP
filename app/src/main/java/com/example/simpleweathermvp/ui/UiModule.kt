package com.example.simpleweathermvp.ui

import com.example.simpleweathermvp.ui.home.HomePresenter
import com.example.simpleweathermvp.ui.home.HomeView
import com.example.simpleweathermvp.ui.weather.WeatherPresenter
import com.example.simpleweathermvp.ui.weather.WeatherView
import org.koin.dsl.module

object UiModule {

    fun create() = module {
        factory { (homeView: HomeView) -> HomePresenter(homeView, get(), get(), get(), get(), get()) }

        //weather
        factory { (weatherView: WeatherView) -> WeatherPresenter(weatherView, get(), get()) }
    }
}