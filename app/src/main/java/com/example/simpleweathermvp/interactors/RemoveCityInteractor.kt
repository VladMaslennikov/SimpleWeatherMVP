package com.example.simpleweathermvp.interactors

import com.example.simpleweathermvp.data.geteway.CityGeteway
import com.example.simpleweathermvp.entity.City

class RemoveCityInteractor(private val cityGeteway: CityGeteway) {

    fun execute(city: City){
        cityGeteway.removeCity(city)
    }
}