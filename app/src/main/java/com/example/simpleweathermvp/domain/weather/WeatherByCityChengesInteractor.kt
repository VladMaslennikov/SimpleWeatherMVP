package com.example.simpleweathermvp.domain.weather

import com.example.simpleweathermvp.data.dto.toWeatherUi
import com.example.simpleweathermvp.data.geteway.WeatherGeteway
import com.example.simpleweathermvp.domain.entity.City
import com.example.simpleweathermvp.ui.entity.WeatherUi
import io.reactivex.Flowable
import java.text.SimpleDateFormat
import java.util.*

class WeatherByCityChengesInteractor(val weatherGeteway: WeatherGeteway) {

    fun execute(city: City): Flowable<List<WeatherUi>> {

        return weatherGeteway.chengesWeatherByCityId(city)
                .map {
                    it.map {
                        it.toWeatherUi()
                    }
                }
    }
}