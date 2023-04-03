package com.example.popularlibrariesapp.data.datacash.room

import androidx.room.*
import com.example.popularlibrariesapp.domain.dto.room.RoomGithubUserEntity

@Dao
interface GithubUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGithubUserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomGithubUserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGithubUserEntity>)

    @Update
    fun update(user: RoomGithubUserEntity)

    @Update
    fun update(vararg users: RoomGithubUserEntity)

    @Update
    fun update(users: List<RoomGithubUserEntity>)

    @Delete
    fun delete(user: RoomGithubUserEntity)

    @Delete
    fun delete(vararg users: RoomGithubUserEntity)

    @Delete
    fun delete(users: List<RoomGithubUserEntity>)

    @Query("SELECT * FROM RoomGithubUserEntity")
    fun getAll(): List<RoomGithubUserEntity>

    @Query("SELECT * FROM RoomGithubUserEntity WHERE login = :login LIMIT 1")
    fun findByLogin(login: String): RoomGithubUserEntity?
}