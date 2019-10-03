package com.example.simpleweathermvp.model;

import com.example.simpleweathermvp.entity.City;
import com.example.simpleweathermvp.model.network.GooglePlacesApi;
import com.example.simpleweathermvp.model.network.response.CityResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class CityModel {

    private final String TYPES = "(cities)";
    private final String LANGUAGE = "ru_RU";
    private final String KEY = "AIzaSyC4LLSpwSWJrw6oKaPUOhY4ElpgEfUGDXE";

    public CityModel(GooglePlacesApi googlePlacesApi) {
        this.googlePlacesApi = googlePlacesApi;
    }

    private GooglePlacesApi googlePlacesApi;

    public Observable<List<City>> getCitySuggestion(String qwery) {
        return googlePlacesApi.getCities(qwery, TYPES, LANGUAGE, KEY)
                .map(new Function<CityResponse, List<City>>() {
                    @Override
                    public List<City> apply(CityResponse cityResponse) throws Exception {
                        List<City> result = new ArrayList<>();

                        for (CityResponse.Prediction prediction: cityResponse.getPredictions()) {
                            String placeId = prediction.getPlaceId();
                            String city = prediction.getStructuredFormatting().getMainText();
                            String country = prediction.getStructuredFormatting().getSecondaryText();

                            result.add(new City(placeId, city, country));
                        }

                        return result;
                    }
                }
                );
    }
}
