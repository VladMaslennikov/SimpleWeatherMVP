package com.example.simpleweathermvp.ui

import com.example.simpleweathermvp.ui.home.HomeActivity
import com.example.simpleweathermvp.ui.home.HomePresenter
import com.example.simpleweathermvp.ui.home.HomeView
import org.koin.dsl.module

object UiModule {

    fun create() = module {
        factory { (homeView: HomeView) -> HomePresenter(get(), homeView) }
    }
}