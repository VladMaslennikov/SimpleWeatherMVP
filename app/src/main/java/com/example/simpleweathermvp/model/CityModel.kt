package com.example.simpleweathermvp.model

import com.example.simpleweathermvp.entity.City
import com.example.simpleweathermvp.model.network.GooglePlacesApi
import com.example.simpleweathermvp.model.network.response.CityResponse

import io.reactivex.Observable
import io.reactivex.functions.Function

class CityModel(val googlePlacesApi: GooglePlacesApi) {

    private val TYPES: String = "(cities)"
    private val LANGUAGE: String = "ru_RU"
    private val KEY: String = "AIzaSyC4LLSpwSWJrw6oKaPUOhY4ElpgEfUGDXE"

    fun getCitySuggestion(query: String): Observable<List<City>> {
        return googlePlacesApi.getCities(query, TYPES, LANGUAGE, KEY)
                .map {cityResponse ->
                    cityResponse.predictions
                            .map { prediction ->
                                City(
                                        id = prediction.id,
                                        name = prediction.structuredFormatting.mainText,
                                        country = prediction.structuredFormatting.secondaryText
                                )
                            }
                }
    }

}