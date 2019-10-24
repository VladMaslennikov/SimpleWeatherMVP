package com.example.simpleweathermvp.model

import com.example.simpleweathermvp.model.network.GooglePlacesApi
import org.koin.dsl.module

object ModelModule {
    fun create() = module {
        single { CityModel(GooglePlacesApi.create())}
    }
}