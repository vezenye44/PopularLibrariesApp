package com.example.popularlibrariesapp.data.room

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.popularlibrariesapp.domain.dto.room.RoomGithubUserEntity
import com.example.popularlibrariesapp.domain.dto.room.RoomGithubUserReposEntity

@androidx.room.Database(
    entities = [
        RoomGithubUserEntity::class,
        RoomGithubUserReposEntity::class
    ],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract val userDao: GithubUserDao
    abstract val userRepoDao: GithubUserRepoDao

    companion object {
        private const val DB_NAME = "database.db"
        private var instance: Database? = null
        fun getInstance() = instance
            ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context!!, Database::class.java,
                    DB_NAME
                )
                    .build()
            }
        }
    }
}