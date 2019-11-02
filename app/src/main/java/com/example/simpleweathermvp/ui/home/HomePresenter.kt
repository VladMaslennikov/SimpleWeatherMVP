package com.example.simpleweathermvp.ui.home

import android.annotation.SuppressLint
import android.util.Log
import com.example.simpleweathermvp.entity.City
import com.example.simpleweathermvp.interactors.AddCity
import com.example.simpleweathermvp.interactors.GetAllCities
import com.example.simpleweathermvp.interactors.RemoveCityInteractor
import com.example.simpleweathermvp.model.CityModel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class HomePresenter(
        private val cityModel: CityModel,
        private val homeView: HomeView,
        private val addCity: AddCity,
        private val getAllCities: GetAllCities,
        private val removeCityInteractor: RemoveCityInteractor

) {


    @SuppressLint("CheckResult")
    fun onSuggvestionQuery(query: String){
        cityModel.getCitySuggestion(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    homeView?.showSuggvestionCities(it)
                }, {
                    Log.e("App", "Error", it)
                })
    }

    fun savedCityDb(city: City){
        addCity.execute(city)
    }

    @SuppressLint("CheckResult")
    fun getAllCities(){
        getAllCities.execute()
                .subscribe(Consumer {

                    homeView.showListCicies(it.map { cityTable ->
                        City(
                                id = cityTable.id,
                                name = cityTable.city,
                                country = cityTable.country
                        )
                    })
                })
    }

    fun removeCity(city: City){
        removeCityInteractor.execute(city)
    }
}