package com.example.simpleweathermvp.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "citytable")
class CityData(

        @PrimaryKey
        val id: String,

        val name: String,
        val country: String,
        val lat: Double?,
        val lng: Double?
) {

}