package com.example.simpleweathermvp.data.network

import com.example.simpleweathermvp.data.dto.CityData
import com.example.simpleweathermvp.data.network.response.toCityData
import com.example.simpleweathermvp.data.network.response.toCityDataList
import io.reactivex.Single

class GooglePlacesClient(
        private val googlePlacesApi: GooglePlacesApi
) {

    companion object {
        private const val SUGGESTION_TYPE: String = "(cities)"
        private const val LANGUAGE_RU: String = "ru_RU"
        private const val API_KEY: String = "AIzaSyC4LLSpwSWJrw6oKaPUOhY4ElpgEfUGDXE"

        private const val FIELDS: String = "geometry"
    }

    fun searchCitySuggestions(query: String): Single<List<CityData>> {
        return googlePlacesApi.getCities(query, SUGGESTION_TYPE, LANGUAGE_RU, API_KEY)
                .map { response ->
                    response.toCityDataList()
                }
    }

    fun getLocationCity(cityData: CityData): Single<CityData> {
        return googlePlacesApi.getLocationCity(cityData.id, FIELDS, API_KEY)
                .map {
                    it.toCityData(cityData)
                }
    }
}