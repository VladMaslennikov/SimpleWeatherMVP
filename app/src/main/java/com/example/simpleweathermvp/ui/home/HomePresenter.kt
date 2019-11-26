package com.example.simpleweathermvp.ui.home

import android.annotation.SuppressLint
import android.util.Log
import com.example.simpleweathermvp.data.geteway.CityGateway
import com.example.simpleweathermvp.domain.city.*
import com.example.simpleweathermvp.domain.entity.City

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class HomePresenter(
        private val homeView: HomeView,
        private val getCitySuggestionInteractor: GetCitySuggestionInteractor,
        private val addCityInteractor: AddCityInteractor,
        private val getAllCitiesInteractor: GetAllCitiesInteractor,
        private val removeCityInteractor: RemoveCityInteractor,
        private val getCityLocationInteractor: GetCityLocationInteractor

) {


    @SuppressLint("CheckResult")
    fun onSuggestionQuery(query: String){
        getCitySuggestionInteractor.execute(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    homeView.showSuggvestionCities(it)
                }, {
                    Log.e("App", "Error", it)
                })
    }

    fun onSuggestionCitySelect(city: City) {
        addCityInteractor.execute(city)
    }

    @SuppressLint("CheckResult")
    fun getAllCities(){
        getAllCitiesInteractor.execute()
                .subscribe(Consumer {

                    homeView.showListCicies(it.map { cityTable ->
                        City(
                                id = cityTable.id,
                                name = cityTable.name,
                                country = cityTable.country,
                                lat = cityTable.lat,
                                lng = cityTable.lng
                        )
                    })
                })
    }

    fun removeCity(city: City){
        removeCityInteractor.execute(city)
    }

    @SuppressLint("CheckResult")
    fun clickOnCity(city: City) {

        if (city.lat != null && city.lng != null) {

            homeView.startWeatherActivity(city)

        } else {
            getCityLocationInteractor.execute(city)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        addCityInteractor.execute(it)
                        homeView.startWeatherActivity(it)
                    }, {
                        Log.e("App", "Error", it)
                    })
        }
    }

}