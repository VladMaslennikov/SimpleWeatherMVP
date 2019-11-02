package com.example.simpleweathermvp.interactors

import com.example.simpleweathermvp.data.database.CityTable
import com.example.simpleweathermvp.data.geteway.CityGeteway
import com.example.simpleweathermvp.entity.City
import io.reactivex.Flowable

class GetAllCities(val cityGeteway: CityGeteway) {

    fun execute(): Flowable<List<CityTable>> {
        return cityGeteway.getAllCities()
    }
}