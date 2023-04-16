package com.example.popularlibrariesapp.dependency_injection.modules

import androidx.room.Room
import com.example.popularlibrariesapp.App
import com.example.popularlibrariesapp.data.datacash.room.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val DB_NAME = "database.db"

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun database(app: App): Database {
        return Room.databaseBuilder(
            app, Database::class.java, DB_NAME
        ).build()
    }
}