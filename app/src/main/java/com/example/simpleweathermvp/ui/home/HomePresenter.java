package com.example.simpleweathermvp.ui.home;

import android.view.View;

import com.example.simpleweathermvp.entity.City;
import com.example.simpleweathermvp.model.CityModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter {

    private CityModel citymodel;
    private HomeView homeView;
    public HomePresenter(CityModel model ) {
        this.citymodel = model;
    }

    void onViewCreated(HomeActivity homeView){
        this.homeView = homeView;
    }

    void onSuggvestionQwery(String qwery){
        citymodel.getCitySuggestion(qwery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<City>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<City> cities) {
                    homeView.showSuggvestionCities(cities);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    void onSuggvestionCityClick(City city){

    }


}
