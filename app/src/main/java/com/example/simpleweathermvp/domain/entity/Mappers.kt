package com.example.simpleweathermvp.domain.entity

import com.example.simpleweathermvp.data.dto.CityData

fun City.toCityData(): CityData {
    return CityData(
            id = id,
            name = name,
            country = country,
            lat = lat,
            lng = lng
    )
}