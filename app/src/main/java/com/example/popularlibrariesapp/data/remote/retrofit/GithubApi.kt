package com.example.popularlibrariesapp.data.remote.retrofit

import com.example.popularlibrariesapp.domain.dto.GithubUserEntity
import com.example.popularlibrariesapp.domain.dto.GithubUserReposEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("/users")
    fun getUsers(): Single<List<GithubUserEntity>>

    @GET("users/{user}/repos")
    fun getListRepos(@Path("user") user: String): Single<List<GithubUserReposEntity>>
}