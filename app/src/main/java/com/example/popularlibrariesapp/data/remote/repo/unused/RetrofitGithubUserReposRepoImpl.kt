package com.example.popularlibrariesapp.data.remote.repo.unused

import com.example.popularlibrariesapp.data.remote.retrofit.GithubApi
import com.example.popularlibrariesapp.domain.remote.repo.GithubUserReposRepo
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUserReposRepoImpl(
    private val api: GithubApi
) : GithubUserReposRepo {
    override fun getUserRepos(userName: String) =
        api.getListRepos(userName).subscribeOn(Schedulers.io())

}