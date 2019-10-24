package com.example.simpleweathermvp

import android.app.Application
import com.example.simpleweathermvp.model.ModelModule
import com.example.simpleweathermvp.ui.UiModule
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                    listOf(
                            UiModule.create(),
                            ModelModule.create()

                    )
            )
        }
    }
}