package com.example.simpleweathermvp.data.dto

import com.example.simpleweathermvp.R
import com.example.simpleweathermvp.domain.entity.City
import com.example.simpleweathermvp.ui.entity.WeatherUi
import java.text.SimpleDateFormat
import java.util.*

fun List<CityData>.toCityList(): List<City> {
    return map { cityData ->
        cityData.toCity()
    }
}

fun CityData.toCity(): City {
    return City(
            id = id,
            name = name,
            country = country,
            lat = lat,
            lng = lng
    )
}

fun WeatherData.toWeatherUi(): WeatherUi {

    fun getImageToday(icon: String) = when (icon) {
        "01d" -> R.drawable.ic_sun_140
        "01n" -> R.drawable.ic_moon_140
        "02d" -> R.drawable.ic_partly_cloudy_d_140
        "02n" -> R.drawable.ic_partly_cloudy_140
        "03d", "03n" -> R.drawable.ic_mostly_cloudy_1_140
        "04d", "04n" -> R.drawable.ic_mostly_cloudy_2_140
        "09d", "09n" -> R.drawable.ic_heavy_rain_140
        "10d" -> R.drawable.ic_heavy_rain_day_140
        "10n" -> R.drawable.ic_heavy_rain_night_140
        "11d" -> R.drawable.ic_thunderstorm_day_140
        "11n" -> R.drawable.ic_thunderstorm_night_140
        "13d" -> R.drawable.ic_snow_day_140
        "13n" -> R.drawable.ic_snow_night_140
        "50d", "50n" -> R.drawable.ic_mist_140
        else -> R.drawable.ic_close_black_24dp
    }
    
    fun getImageforFiveDay(icon: String) = when (icon) {
        "01d", "01n" -> R.drawable.ic_sun_32
        "02d", "02n" -> R.drawable.ic_partly_cloudy_d_32
        "03d", "03n" -> R.drawable.ic_mostly_cloudy_32
        "04d", "04n" -> R.drawable.ic_mostly_cloudy_2_32
        "09d", "09n" -> R.drawable.ic_heavy_rain_32
        "10d", "10n" -> R.drawable.ic_heavy_rain_day_32
        "11d", "11n" -> R.drawable.ic_thunderstorm_day_32
        "13d", "13n" -> R.drawable.ic_snow_day_32
        "50d", "50n" -> R.drawable.ic_mist_32
        else -> R.drawable.ic_close_black_24dp

    }

    val formatDay = SimpleDateFormat("E", Locale.ENGLISH)
    val formatLastUpdate = SimpleDateFormat("d MMM HH:mm", Locale.ENGLISH)
    val formatSunTime = SimpleDateFormat("HH:mm", Locale.ENGLISH)

    return WeatherUi(
            id = id,
            temp = (temp - 273.15).toInt().toString(),
            description = description,
            iconToday = getImageToday(iconToday),

            pressure = pressure.toString(),
            humidity = humidity.toString(),
            windSpeed = windSpeed.toString(),

            oneDay = formatDay.format(Date(oneDay*1000)),
            tempOneDay = (tempOneDay - 273.15).toInt().toString(),
            iconOneDay = getImageforFiveDay(iconOneDay),

            twoDay = formatDay.format(Date(twoDay*1000)),
            tempTwoDay = (tempTwoDay - 273.15).toInt().toString(),
            iconTwoDay = getImageforFiveDay(iconTwoDay),

            threeDay = formatDay.format(Date(threeDay*1000)),
            tempThreeDay = (tempThreeDay - 273.15).toInt().toString(),
            iconThreeDay = getImageforFiveDay(iconThreeDay),

            fourDay = formatDay.format(Date(fourDay*1000)),
            tempFourDay = (tempFourDay - 273.15).toInt().toString(),
            iconFourDay = getImageforFiveDay(iconFourDay),

            fiveDay = formatDay.format(Date(fiveDay*1000)),
            tempFiveDay = (tempFiveDay - 273.15).toInt().toString(),
            iconFiveDay = getImageforFiveDay(iconFiveDay),

            lastUpdate = formatLastUpdate.format(Date(lastUpdate)),
            sunrise = formatSunTime.format(Date(sunrise*1000)),
            sunset = formatSunTime.format(Date(sunset*1000))
    )
}