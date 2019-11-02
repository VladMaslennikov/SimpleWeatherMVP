package com.example.simpleweathermvp

import android.app.Application
import com.example.simpleweathermvp.data.DataModule
import com.example.simpleweathermvp.model.ModelModule
import com.example.simpleweathermvp.ui.UiModule
import org.koin.core.context.startKoin
import com.facebook.stetho.Stetho
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import org.koin.android.ext.koin.androidContext


class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                    listOf(
                            UiModule.create(),
                            ModelModule.create(),
                            DataModule.create()

                    )
            )
            androidContext(this@App)
        }

        // Create an InitializerBuilder
        val initializerBuilder = Stetho.newInitializerBuilder(this)

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        )

        // Enable command line interface
        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)
        )

        // Use the InitializerBuilder to generate an Initializer
        val initializer = initializerBuilder.build()

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer)
    }


}