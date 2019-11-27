package com.example.simpleweathermvp.domain.city

import com.example.simpleweathermvp.data.geteway.CityGateway
import com.example.simpleweathermvp.domain.entity.City
import io.reactivex.Single

class GetCityLocationInteractor(private val cityGateway: CityGateway) {

    fun execute(city: City): Single<City> {
        return cityGateway.getCityLocation(city)
    }
}