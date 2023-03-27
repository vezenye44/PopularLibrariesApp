package com.example.popularlibrariesapp.data.datacash

import com.example.popularlibrariesapp.data.room.Database
import com.example.popularlibrariesapp.domain.datacash.IUserCache
import com.example.popularlibrariesapp.domain.dto.GithubUserEntity
import com.example.popularlibrariesapp.domain.dto.room.RoomGithubUserEntity

class RoomUserCache(private val db: Database) : IUserCache {
    override fun loadUsers() = db.userDao.getAll().map { roomUser ->
        GithubUserEntity(
            id = roomUser.id,
            login = roomUser.login,
            avatarUrl = roomUser.avatarUrl,
            reposUrl = roomUser.reposUrl
        )
    }

    override fun saveUsers(users: List<GithubUserEntity>): List<GithubUserEntity> {
        val roomUsers = users.map { user ->
            RoomGithubUserEntity(
                id = user.id ?: "",
                login = user.login ?: "",
                avatarUrl = user.avatarUrl ?: "",
                reposUrl = user.reposUrl ?: ""
            )
        }
        db.userDao.insert(roomUsers)
        return users
    }
}