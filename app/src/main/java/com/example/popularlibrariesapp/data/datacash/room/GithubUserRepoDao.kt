package com.example.popularlibrariesapp.data.datacash.room

import androidx.room.*
import com.example.popularlibrariesapp.domain.dto.room.RoomGithubUserReposEntity

@Dao
interface GithubUserRepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: RoomGithubUserReposEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg users: RoomGithubUserReposEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<RoomGithubUserReposEntity>)

    @Update
    fun update(user: RoomGithubUserReposEntity)

    @Update
    fun update(vararg users: RoomGithubUserReposEntity)

    @Update
    fun update(users: List<RoomGithubUserReposEntity>)

    @Delete
    fun delete(user: RoomGithubUserReposEntity)

    @Delete
    fun delete(vararg users: RoomGithubUserReposEntity)

    @Delete
    fun delete(users: List<RoomGithubUserReposEntity>)

    @Query("SELECT * FROM RoomGithubUserReposEntity")
    fun getAll(): List<RoomGithubUserReposEntity>

    @Query("SELECT * FROM RoomGithubUserReposEntity WHERE userId = :userId")
    fun findForUser(userId: String): List<RoomGithubUserReposEntity>
}