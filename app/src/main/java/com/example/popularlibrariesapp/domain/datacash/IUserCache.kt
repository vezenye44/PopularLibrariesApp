package com.example.popularlibrariesapp.domain.datacash

import com.example.popularlibrariesapp.domain.dto.GithubUserEntity

interface IUserCache {
    fun saveUsers(users: List<GithubUserEntity>): List<GithubUserEntity>
    fun loadUsers(): List<GithubUserEntity>
}