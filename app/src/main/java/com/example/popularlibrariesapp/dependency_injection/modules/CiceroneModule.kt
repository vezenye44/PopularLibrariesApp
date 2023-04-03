package com.example.popularlibrariesapp.dependency_injection.modules

import com.example.popularlibrariesapp.ui.interfaces.navigate.IScreens
import com.example.popularlibrariesapp.ui.main.AndroidScreens
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CiceroneModule {

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }

    @Singleton
    @Provides
    fun navigatorHolder() = cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun router() = cicerone.router

    @Singleton
    @Provides
    fun appScreens() : IScreens{
        return AndroidScreens()
    }
}
