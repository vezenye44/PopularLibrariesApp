package com.example.popularlibrariesapp.data.remote.repo.unused

import com.example.popularlibrariesapp.data.remote.retrofit.GithubApi
import com.example.popularlibrariesapp.domain.remote.repo.GithubUsersRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepoImpl(private val api: GithubApi) : GithubUsersRepo {

    override fun getUsers() = api.getUsers().subscribeOn(Schedulers.io())
}