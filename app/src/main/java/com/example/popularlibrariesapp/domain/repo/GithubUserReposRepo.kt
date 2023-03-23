package com.example.popularlibrariesapp.domain.repo

import com.example.popularlibrariesapp.domain.dto.GithubUserReposEntity
import io.reactivex.rxjava3.core.Single

interface GithubUserReposRepo {
    fun getUserRepos(userName: String): Single<List<GithubUserReposEntity>>
}
