package com.example.simpleweathermvp.data

import com.example.simpleweathermvp.data.database.AppDatabase
import com.example.simpleweathermvp.data.geteway.CityGateway
import com.example.simpleweathermvp.data.geteway.WeatherGeteway
import com.example.simpleweathermvp.domain.weather.WeatherByCityChengesInteractor
import com.example.simpleweathermvp.domain.weather.WeatherByCityRefreshInteractor
import com.example.simpleweathermvp.data.network.GooglePlacesApi
import com.example.simpleweathermvp.data.network.GooglePlacesClient
import com.example.simpleweathermvp.data.network.WeatherApi
import com.example.simpleweathermvp.data.network.WeatherClient
import com.example.simpleweathermvp.domain.city.*
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DataModule {

    fun create() = module{
        single { GooglePlacesClient(GooglePlacesApi.create()) }
        single { CityGateway(get(), get<AppDatabase>().cityDao()) }
        single { AppDatabase.newInstance(androidContext()) }
        factory { AddCityInteractor(get()) }
        factory { GetAllCitiesInteractor(get()) }
        factory { GetCityLocationInteractor(get()) }
        factory { GetCitySuggestionInteractor(get()) }
        factory { RemoveCityInteractor(get()) }

        //Weather
        factory { WeatherByCityRefreshInteractor(get())}
        factory { WeatherByCityChengesInteractor(get())}
        single { WeatherClient(WeatherApi.create())}
        single { WeatherGeteway(get() , get<AppDatabase>().weatherDao()) }
    }
}