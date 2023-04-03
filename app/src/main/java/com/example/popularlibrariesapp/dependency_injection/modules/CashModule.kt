package com.example.popularlibrariesapp.dependency_injection.modules

import android.content.Context
import androidx.room.Room
import com.example.popularlibrariesapp.App
import com.example.popularlibrariesapp.data.datacash.RoomUserCache
import com.example.popularlibrariesapp.data.datacash.RoomUserRepositoriesCache
import com.example.popularlibrariesapp.data.room.Database
import com.example.popularlibrariesapp.domain.datacash.IUserCache
import com.example.popularlibrariesapp.domain.datacash.IUserRepositoriesCache
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val DB_NAME = "database.db"

@Module
class CashModule {

    @Singleton
    @Provides
    fun database(app: App): Database {
        return Room.databaseBuilder(
            app, Database::class.java, DB_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun userCash(db: Database): IUserCache {
        return RoomUserCache(db)
    }

    @Singleton
    @Provides
    fun reposCash(db: Database): IUserRepositoriesCache {
        return RoomUserRepositoriesCache(db)
    }
}