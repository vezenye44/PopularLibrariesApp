package com.example.popularlibrariesapp.domain.repo

import com.example.popularlibrariesapp.domain.dto.GithubUserEntity
import io.reactivex.rxjava3.core.Single

interface GithubUsersRepo {

    fun getUsers(): Single<List<GithubUserEntity>>
}