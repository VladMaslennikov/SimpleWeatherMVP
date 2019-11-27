package com.example.simpleweathermvp.domain.city

import com.example.simpleweathermvp.data.geteway.CityGateway
import com.example.simpleweathermvp.domain.entity.City

class RemoveCityInteractor(private val cityGateway: CityGateway) {

    fun execute(city: City){
        cityGateway.removeCity(city)
    }
}