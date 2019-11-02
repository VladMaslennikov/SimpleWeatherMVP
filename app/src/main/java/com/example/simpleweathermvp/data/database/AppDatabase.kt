package com.example.simpleweathermvp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CityTable::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    companion object{

        fun newInstance(context: Context): AppDatabase{
            return Room.databaseBuilder<AppDatabase>(context, AppDatabase::class.java, "appDatabase")
                    .build()
        }
    }

    abstract fun cityDao(): CityDao
}