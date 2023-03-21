package com.example.popularlibrariesapp.data.retrofit

import com.example.popularlibrariesapp.domain.dto.GithubUserEntity
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface GithubApi {
    @GET("/users")
    fun getUsers(): Single<List<GithubUserEntity>>
}