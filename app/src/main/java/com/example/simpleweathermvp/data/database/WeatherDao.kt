package com.example.simpleweathermvp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.simpleweathermvp.data.dto.WeatherData
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weathertable WHERE id = :id")
    fun getById(id: String): Flowable<List<WeatherData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherData: WeatherData): Completable
}