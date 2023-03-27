package com.example.popularlibrariesapp.data.repo

import com.example.popularlibrariesapp.data.retrofit.GithubApi
import com.example.popularlibrariesapp.domain.datacash.IUserCache
import com.example.popularlibrariesapp.domain.internet.INetworkStatus
import com.example.popularlibrariesapp.domain.repo.GithubUsersRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class CashedRetrofitGithubUsersRepoImpl(
    private val api: GithubApi,
    private val networkStatus: INetworkStatus,
    private val userCash: IUserCache
) : GithubUsersRepo {
    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        userCash.saveUsers(users)
                    }
                }
        } else {
            Single.fromCallable {
                userCash.loadUsers()
            }
        }
    }.subscribeOn(Schedulers.io())
}