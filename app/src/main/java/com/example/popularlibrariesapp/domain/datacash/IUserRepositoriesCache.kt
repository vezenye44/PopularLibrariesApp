package com.example.popularlibrariesapp.domain.datacash

import com.example.popularlibrariesapp.domain.dto.GithubUserReposEntity

interface IUserRepositoriesCache {
    fun saveUserRepos(
        userName: String,
        repositories: List<GithubUserReposEntity>
    ): List<GithubUserReposEntity>

    fun loadUserRepos(userName: String): List<GithubUserReposEntity>
}