package com.example.simpleweathermvp.domain.city

import com.example.simpleweathermvp.data.geteway.CityGateway
import com.example.simpleweathermvp.domain.entity.City
import io.reactivex.Single

class GetCitySuggestionInteractor(private val cityGateway: CityGateway) {

    fun execute(query: String): Single<List<City>> {
        return cityGateway.getCitySuggestion(query)
    }
}