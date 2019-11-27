package com.example.simpleweathermvp.domain.city

import com.example.simpleweathermvp.data.dto.CityData
import com.example.simpleweathermvp.data.geteway.CityGateway
import io.reactivex.Flowable

class GetAllCitiesInteractor(val cityGateway: CityGateway) {

    fun execute(): Flowable<List<CityData>> {
        return cityGateway.getAllCities()
    }
}