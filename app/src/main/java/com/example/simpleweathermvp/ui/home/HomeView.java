package com.example.simpleweathermvp.ui.home;

import com.example.simpleweathermvp.entity.City;

import java.util.List;

public interface HomeView {

    void showSuggvestionCities(List<City> cities);

    void showCityList(List<City> cities);

    void showError(String message);

    void showEmptyView();
}
