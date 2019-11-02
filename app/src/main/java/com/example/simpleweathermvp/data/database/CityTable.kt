package com.example.simpleweathermvp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "citytable")
class CityTable(

        @PrimaryKey
        val id: String,

        val city: String,
        val country: String,
        val weatherId: Int?
) {

}