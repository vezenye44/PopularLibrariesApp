package com.example.popularlibrariesapp

import android.app.Application
import com.example.popularlibrariesapp.data.internet.AndroidNetworkStatus
import com.example.popularlibrariesapp.data.room.Database
import com.example.popularlibrariesapp.domain.internet.INetworkStatus
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    //TODO : Переместить
    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    lateinit var database: Database
    lateinit var networkStatus: INetworkStatus
    override fun onCreate() {
        super.onCreate()
        instance = this
        networkStatus = AndroidNetworkStatus(this)
        Database.create(this)
        database = Database.getInstance()
    }
}
