package com.example.popularlibrariesapp

import android.app.Application
import com.example.popularlibrariesapp.dependency_injection.components.AppComponent
import com.example.popularlibrariesapp.dependency_injection.components.DaggerAppComponent
import com.example.popularlibrariesapp.dependency_injection.modules.AppModule

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}
