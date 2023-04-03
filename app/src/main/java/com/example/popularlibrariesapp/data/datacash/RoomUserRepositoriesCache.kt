package com.example.popularlibrariesapp.data.datacash

import com.example.popularlibrariesapp.data.datacash.room.Database
import com.example.popularlibrariesapp.domain.datacash.IUserRepositoriesCache
import com.example.popularlibrariesapp.domain.dto.GithubUserReposEntity
import com.example.popularlibrariesapp.domain.dto.room.RoomGithubUserReposEntity

class RoomUserRepositoriesCache(private val db: Database) : IUserRepositoriesCache {

    override fun loadUserRepos(userName: String): List<GithubUserReposEntity> {
        val roomUser = db.userDao.findByLogin(userName)
            ?: throw RuntimeException("No such user in cache")
        return db.userRepoDao.findForUser(roomUser.id).map {
            GithubUserReposEntity(
                id = it.id,
                name = it.name,
                forksCount = it.forksCount,
                userId = it.userId
            )
        }
    }

    override fun saveUserRepos(
        userName: String,
        repositories: List<GithubUserReposEntity>
    ): List<GithubUserReposEntity> {
        val roomUser = db.userDao.findByLogin(userName)
            ?: throw RuntimeException("No such user in cache")
        val roomRepos = repositories.map {
            RoomGithubUserReposEntity(
                id = it.id ?: "",
                name = it.name ?: "",
                forksCount = (it.forksCount ?: 0) as String,
                userId = roomUser.id
            )
        }
        db.userRepoDao.insert(roomRepos)
        return repositories
    }
}