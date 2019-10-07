package com.example.simpleweathermvp.ui.home

import com.example.simpleweathermvp.entity.City

interface HomeView {
     fun showSuggvestionCities(cities: List<City>)
}