package com.example.simpleweathermvp.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.simpleweathermvp.model.database.WeatherAppContract.CityEntry;
import static com.example.simpleweathermvp.model.database.WeatherAppContract.WeatherEntry;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CityDb";
    public static final int DATABASE_VERSION = 1;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_CITY_TABLE = "CREATE TABLE " + CityEntry.TABLE_NAME + " ("
                + CityEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + CityEntry.COLUMN_PLACE_ID + " TEXT NOT NULL, "
                + CityEntry.COLUMN_CITY + " TEXT NOT NULL, "
                + CityEntry.COLUMN_COUNTRY + " TEXT NOT NULL, "
                + CityEntry.COLUMN_WEATHER_ID + " INTEGER NOT NULL);";

        String SQL_CREATE_WEATHER_TABLE = "CREATE TABLE " + WeatherEntry.TABLE_NAME + " ("
                + WeatherEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + WeatherEntry.COLUMN_TEMP + " REAL NOT NULL, "
                + WeatherEntry.COLUMN_HUMIDITY + " INTEGER NOT NULL, "
                + WeatherEntry.COLUMN_WIND_SPEED + " INTEGER NOT NULL, "
                + WeatherEntry.COLUMN_TIME + " INTEGER NOT NULL, "
                + WeatherEntry.COLUMN_CITY_ID + " INTEGER NOT NULL);";

        db.execSQL(SQL_CREATE_CITY_TABLE);
        db.execSQL(SQL_CREATE_WEATHER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);

        onCreate(sqLiteDatabase);
    }
}

