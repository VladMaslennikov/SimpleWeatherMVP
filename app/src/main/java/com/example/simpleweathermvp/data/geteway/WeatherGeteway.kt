package com.example.simpleweathermvp.data.geteway

import android.annotation.SuppressLint
import android.util.Log
import com.example.simpleweathermvp.data.database.WeatherDao
import com.example.simpleweathermvp.data.dto.WeatherData
import com.example.simpleweathermvp.domain.entity.City
import com.example.simpleweathermvp.data.network.WeatherClient
import com.example.simpleweathermvp.data.network.response.WeatherFiveDaysResponse
import com.example.simpleweathermvp.data.network.response.WeatherTodayResponse
import com.example.simpleweathermvp.data.network.response.toWeatherData
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers
import java.util.*

class WeatherGeteway(private val weatherClient: WeatherClient,
                     private val weatherDao: WeatherDao) {

    @SuppressLint("CheckResult")
    fun refreshWeatherByCity(city: City){

        Observable.zip(
                weatherClient.getWeatherFiveDays(city.lat!!, city.lng!!),
                weatherClient.getWeatherToday(city.lat!!, city.lng!!),
                BiFunction<WeatherFiveDaysResponse, WeatherTodayResponse, WeatherData> {
                    t1: WeatherFiveDaysResponse, t2: WeatherTodayResponse ->  mergerResponse(city, t1, t2)
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    weatherDao.insert(it)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe()
                },{

                })
    }

    fun chengesWeatherByCityId(city: City): Flowable<List<WeatherData>> {
        return weatherDao.getById(city.id)
                .observeOn(AndroidSchedulers.mainThread())

    }

    private fun mergerResponse(city: City,
                               weatherFiveDaysResponse: WeatherFiveDaysResponse,
                               weatherTodayResponse: WeatherTodayResponse): WeatherData {

        return weatherTodayResponse.toWeatherData(city, weatherFiveDaysResponse)
    }
}