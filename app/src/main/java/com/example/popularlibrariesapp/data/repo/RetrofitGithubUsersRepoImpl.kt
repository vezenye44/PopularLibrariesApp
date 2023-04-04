package com.example.popularlibrariesapp.data.repo

import com.example.popularlibrariesapp.data.retrofit.GithubApi
import com.example.popularlibrariesapp.domain.repo.GithubUsersRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepoImpl(private val api: GithubApi) : GithubUsersRepo {

    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
}