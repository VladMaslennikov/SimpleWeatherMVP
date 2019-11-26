package com.example.simpleweathermvp.data.geteway

import com.example.simpleweathermvp.data.database.CityDao
import com.example.simpleweathermvp.data.dto.CityData
import com.example.simpleweathermvp.data.dto.toCity
import com.example.simpleweathermvp.data.dto.toCityList
import com.example.simpleweathermvp.domain.entity.City
import com.example.simpleweathermvp.data.network.GooglePlacesClient
import com.example.simpleweathermvp.domain.entity.toCityData
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CityGateway(
        private val googlePlacesClient: GooglePlacesClient,
        private val cityDao: CityDao
) {

    fun getCitySuggestion(query: String): Single<List<City>> {
        return googlePlacesClient.searchCitySuggestions(query)
                .map { cityDataList ->
                    cityDataList.toCityList()
                }
    }

    fun addCity(city: City) {
        cityDao.insert(city.toCityData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

    fun getAllCities(): Flowable<List<CityData>>{
        return cityDao.getAll()
                    .observeOn(AndroidSchedulers.mainThread())

    }

    fun getCityLocation(city: City): Single<City> {
        return googlePlacesClient.getLocationCity(city.toCityData())
                .map {
                    it.toCity()
                }
    }

    fun removeCity(city: City){
        val cityTable = CityData(city.id, city.name, city.country, null, null)
        cityDao.delete(cityTable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
}