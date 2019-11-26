package com.example.simpleweathermvp.domain.entity

class Weather(

        val id: String,

        val temp: String,
        val description: String,

        val pressure: String, // Давление
        val humidity: String, // Влажность
        val windSpeed: String, // Скорость ветра

        val oneDay: String,
        val tempOneDay: String,
        val twoDay: String,
        val tempTwoDay: String,
        val threeDay: String,
        val tempThreeDay: String,
        val fourDay: String,
        val tempFourDay: String,
        val fiveDay: String,
        val tempFiveDay: String,

        val lastUpdate: String,

        val sunrise: String,
        val sunset: String
) {
}