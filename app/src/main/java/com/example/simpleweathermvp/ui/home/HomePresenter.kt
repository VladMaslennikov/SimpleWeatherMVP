package com.example.simpleweathermvp.ui.home

import android.util.Log
import com.example.simpleweathermvp.entity.City
import com.example.simpleweathermvp.model.CityModel

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenter(private val cityModel: CityModel) {

    private var homeView: HomeView? = null

    fun onViewCreated(homeView: HomeActivity) {
        this.homeView = homeView
    }

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
}