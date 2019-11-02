package com.example.simpleweathermvp.interactors

import com.example.simpleweathermvp.data.geteway.CityGeteway
import com.example.simpleweathermvp.entity.City

class AddCity(val cityGeteway: CityGeteway) {

    fun execute(city: City){
        cityGeteway.addCity(city)
    }
}