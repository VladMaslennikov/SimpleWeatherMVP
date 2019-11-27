package com.example.simpleweathermvp.data.network.response

import com.example.simpleweathermvp.data.dto.CityData
import com.example.simpleweathermvp.data.dto.WeatherData
import com.example.simpleweathermvp.domain.entity.City
import java.util.*

fun CitySuggestionResponse.toCityDataList(): List<CityData> {
    return predictions.map { prediction ->
        prediction.toCityData()
    }
}

fun CitySuggestionResponse.Prediction.toCityData(): CityData {
    return CityData(
            id = placeId,
            name = structuredFormatting.mainText,
            country = structuredFormatting.secondaryText,
            lat = null,
            lng = null
    )
}

fun CityResponse.toCityData(cityData: CityData): CityData {
    return CityData(
            id = cityData.id,
            name = cityData.name,
            country = cityData.country,
            lat = result.geometry.location.lat,
            lng = result.geometry.location.lng
    )
}

fun WeatherTodayResponse.toWeatherData(city: City, weatherFiveDaysResponse: WeatherFiveDaysResponse): WeatherData {

    val size = weatherFiveDaysResponse.list.size
    return WeatherData(
            id = city.id,
            temp = main.temp,
            description = weather[0].description,
            iconToday = weather[0].icon,

            pressure = main.pressure,
            humidity = main.humidity,
            windSpeed = wind.speed.toInt(),

            oneDay = weatherFiveDaysResponse.list.get(size - 32).dt.toLong(),
            tempOneDay = weatherFiveDaysResponse.list.get(size - 32).main.temp,
            iconOneDay = weatherFiveDaysResponse.list.get(size - 32).weather.get(0).icon,

            twoDay = weatherFiveDaysResponse.list.get(size - 24).dt.toLong(),
            tempTwoDay = weatherFiveDaysResponse.list.get(size - 24).main.temp,
            iconTwoDay = weatherFiveDaysResponse.list.get(size - 24).weather.get(0).icon,

            threeDay = weatherFiveDaysResponse.list.get(size - 16).dt.toLong(),
            tempThreeDay = weatherFiveDaysResponse.list.get(size - 16).main.temp,
            iconThreeDay = weatherFiveDaysResponse.list.get(size - 16).weather.get(0).icon,

            fourDay = weatherFiveDaysResponse.list.get(size - 8).dt.toLong(),
            tempFourDay = weatherFiveDaysResponse.list.get(size - 8).main.temp,
            iconFourDay = weatherFiveDaysResponse.list.get(size - 8).weather.get(0).icon,

            fiveDay = weatherFiveDaysResponse.list.get(size - 1).dt.toLong(),
            tempFiveDay = weatherFiveDaysResponse.list.get(size - 1).main.temp,
            iconFiveDay = weatherFiveDaysResponse.list.get(size - 1).weather.get(0).icon,

            lastUpdate = Date().time,
            sunset = sys.sunset.toLong(),
            sunrise = sys.sunrise.toLong()

    )
}