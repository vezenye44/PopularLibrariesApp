package com.example.popularlibrariesapp.data.remote.repo

import com.example.popularlibrariesapp.data.remote.retrofit.GithubApi
import com.example.popularlibrariesapp.domain.datacash.IUserRepositoriesCache
import com.example.popularlibrariesapp.domain.dto.GithubUserReposEntity
import com.example.popularlibrariesapp.domain.remote.INetworkStatus
import com.example.popularlibrariesapp.domain.remote.repo.GithubUserReposRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class CashedRetrofitGithubUserReposRepoImpl(
    private val api: GithubApi,
    private val networkStatus: INetworkStatus,
    private val userReposCash: IUserRepositoriesCache
) : GithubUserReposRepo {
    override fun getUserRepos(userName: String): Single<List<GithubUserReposEntity>> =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                api.getListRepos(userName)
                    .flatMap { repositories ->
                        Single.fromCallable {
                            userReposCash.saveUserRepos(userName, repositories)

                        }
                    }
            } else {
                Single.fromCallable {
                    userReposCash.loadUserRepos(userName)
                }
            }
        }.subscribeOn(Schedulers.io())
}