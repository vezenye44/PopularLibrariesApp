package com.example.popularlibrariesapp.data.repo

import com.example.popularlibrariesapp.data.retrofit.GithubApi
import com.example.popularlibrariesapp.domain.repo.GithubUserReposRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUserReposRepoImpl(
    private val api: GithubApi
) : GithubUserReposRepo {
    override fun getUserRepos(userName: String) =
        api.getListRepos(userName).subscribeOn(Schedulers.io())

}