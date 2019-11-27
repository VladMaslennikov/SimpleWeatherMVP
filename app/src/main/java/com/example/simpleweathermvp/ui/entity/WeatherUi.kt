package com.example.simpleweathermvp.ui.entity

class WeatherUi(
        val id: String,

        val temp: String,
        val description: String,
        val iconToday: Int,

        val pressure: String, // Давление
        val humidity: String, // Влажность
        val windSpeed: String, // Скорость ветра

        val oneDay: String,
        val tempOneDay: String,
        val iconOneDay: Int,

        val twoDay: String,
        val tempTwoDay: String,
        val iconTwoDay: Int,

        val threeDay: String,
        val tempThreeDay: String,
        val iconThreeDay: Int,

        val fourDay: String,
        val tempFourDay: String,
        val iconFourDay: Int,

        val fiveDay: String,
        val tempFiveDay: String,
        val iconFiveDay: Int,

        val lastUpdate: String,

        val sunrise: String,
        val sunset: String
) {
}