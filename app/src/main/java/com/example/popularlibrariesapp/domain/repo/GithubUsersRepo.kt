package com.example.popularlibrariesapp.domain.repo

import com.example.popularlibrariesapp.domain.dto.GithubUser

interface GithubUsersRepo {
    fun getUsers() : List<GithubUser>
}