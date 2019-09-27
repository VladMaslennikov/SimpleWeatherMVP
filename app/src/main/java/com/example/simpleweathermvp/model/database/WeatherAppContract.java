package com.example.simpleweathermvp.model.database;

import android.provider.BaseColumns;

public class WeatherAppContract {

    public static final class CityEntry {

        public static final String TABLE_NAME = "City";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PLACE_ID = "placeId";
        public static final String COLUMN_CITY = "city";
        public static final String COLUMN_COUNTRY = "country";
        public static final String COLUMN_WEATHER_ID = "weatherId";

    }

    public static final class WeatherEntry {

        public static final String TABLE_NAME = "MyWeather";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TEMP  = "temperatyre";
        public static final String COLUMN_HUMIDITY  = "humidity";
        public static final String COLUMN_WIND_SPEED  = "windSpeed";
        public static final String COLUMN_TIME  = "time";
        public static final String COLUMN_CITY_ID = "cityId";



    }
}
