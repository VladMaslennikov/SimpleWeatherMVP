package com.example.simpleweathermvp.model;

import com.example.simpleweathermvp.entity.City;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;


public class CityModel {

    public Observable<List<City>> getCitySuggestion(String qwery){
        List<City> list = new ArrayList<>();

        City city = new City("1","Оренбург","Россия");
        list.add(city);

        return Observable.just(list)
                .delay(2, TimeUnit.SECONDS);
    }
}
