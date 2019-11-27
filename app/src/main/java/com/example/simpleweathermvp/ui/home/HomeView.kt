package com.example.simpleweathermvp.ui.home

import com.example.simpleweathermvp.domain.entity.City

interface HomeView {
     fun showSuggvestionCities(cities: List<City>)
     fun showListCicies(cities: List<City>)
     fun startWeatherActivity(city: City)

}