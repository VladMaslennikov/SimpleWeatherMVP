package com.example.simpleweathermvp.data.database

import androidx.room.*

import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface CityDao {

    @Query("SELECT * FROM citytable")
    fun getAll(): Flowable<List<CityTable>>

    @Query("SELECT * FROM citytable WHERE id = :id")
    fun getById(id: String): CityTable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cityTable: CityTable) : Completable

    @Delete
    fun delete(cityTable: CityTable) : Completable
}