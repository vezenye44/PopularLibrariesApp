package com.example.popularlibrariesapp.dependency_injection.modules

import com.example.popularlibrariesapp.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    fun app(): App = app
}