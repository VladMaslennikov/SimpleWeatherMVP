package com.example.simpleweathermvp.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weathertable")
class WeatherData(

        @PrimaryKey
        val id: String,

        val temp: Double,
        val description: String,
        val iconToday: String,

        val pressure: Int, // Давление
        val humidity: Int, // Влажность
        val windSpeed: Int, // Скорость ветра

        val oneDay: Long,
        val tempOneDay: Double,
        val iconOneDay: String,

        val twoDay: Long,
        val tempTwoDay: Double,
        val iconTwoDay: String,

        val threeDay: Long,
        val tempThreeDay: Double,
        val iconThreeDay: String,

        val fourDay: Long,
        val tempFourDay: Double,
        val iconFourDay: String,

        val fiveDay: Long,
        val tempFiveDay: Double,
        val iconFiveDay: String,

        val lastUpdate: Long,

        val sunrise: Long,
        val sunset: Long

) {
}