package com.example.simpleweathermvp.data

import androidx.room.Room
import com.example.simpleweathermvp.data.database.AppDatabase
import com.example.simpleweathermvp.data.geteway.CityGeteway
import com.example.simpleweathermvp.interactors.AddCity
import com.example.simpleweathermvp.interactors.GetAllCities
import com.example.simpleweathermvp.interactors.RemoveCityInteractor
import com.example.simpleweathermvp.model.network.GooglePlacesApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DataModule {

    fun create() = module{
        single { CityGeteway(GooglePlacesApi.create(), get<AppDatabase>().cityDao()) }
        single { AppDatabase.newInstance(androidContext()) }
        factory { AddCity(get()) }
        factory { GetAllCities(get()) }
        factory { RemoveCityInteractor(get()) }

    }
}