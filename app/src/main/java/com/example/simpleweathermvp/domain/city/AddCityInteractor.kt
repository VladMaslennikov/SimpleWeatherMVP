package com.example.simpleweathermvp.domain.city

import com.example.simpleweathermvp.data.geteway.CityGateway
import com.example.simpleweathermvp.domain.entity.City
import io.reactivex.Completable

class AddCityInteractor(
        private val cityGateway: CityGateway
) {

    fun execute(city: City) {
        return cityGateway.addCity(city)
    }
}