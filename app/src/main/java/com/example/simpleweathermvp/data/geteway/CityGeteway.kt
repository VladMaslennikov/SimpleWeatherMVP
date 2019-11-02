package com.example.simpleweathermvp.data.geteway

import com.example.simpleweathermvp.data.database.AppDatabase
import com.example.simpleweathermvp.data.database.CityDao
import com.example.simpleweathermvp.data.database.CityTable
import com.example.simpleweathermvp.entity.City
import com.example.simpleweathermvp.model.network.GooglePlacesApi
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CityGeteway(val googlePlacesApi: GooglePlacesApi, val cityDao: CityDao) {

    fun addCity(city: City){
        val cityTable = CityTable(city.id, city.name, city.country, null)
        cityDao.insert(cityTable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()

    }

    fun getAllCities(): Flowable<List<CityTable>>{
        return cityDao.getAll()
                    .observeOn(AndroidSchedulers.mainThread())

    }

    fun removeCity(city: City){
        val cityTable = CityTable(city.id, city.name, city.country, null)
        cityDao.delete(cityTable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }
}