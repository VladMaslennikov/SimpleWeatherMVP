package com.example.simpleweathermvp.data.database

import androidx.room.*
import com.example.simpleweathermvp.data.dto.CityData

import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface CityDao {

    @Query("SELECT * FROM citytable")
    fun getAll(): Flowable<List<CityData>>

    @Query("SELECT * FROM citytable WHERE id = :id")
    fun getById(id: String): CityData

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cityData: CityData) : Completable

    @Delete
    fun delete(cityData: CityData) : Completable
}