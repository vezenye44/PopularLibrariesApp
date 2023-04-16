package com.example.popularlibrariesapp.dependency_injection.modules

import com.example.popularlibrariesapp.data.datacash.RoomUserCache
import com.example.popularlibrariesapp.data.datacash.room.Database
import com.example.popularlibrariesapp.data.remote.repo.CashedRetrofitGithubUsersRepoImpl
import com.example.popularlibrariesapp.data.remote.retrofit.GithubApi
import com.example.popularlibrariesapp.dependency_injection.scopes.UsersScope
import com.example.popularlibrariesapp.domain.datacash.IUserCache
import com.example.popularlibrariesapp.domain.remote.INetworkStatus
import com.example.popularlibrariesapp.domain.remote.repo.GithubUsersRepo
import dagger.Module
import dagger.Provides

@Module
class UsersModule {
    @UsersScope
    @Provides
    fun userRepo(
        api: GithubApi, networkStatus: INetworkStatus, userCash: IUserCache
    ): GithubUsersRepo {
        return CashedRetrofitGithubUsersRepoImpl(api, networkStatus, userCash)
    }

    @UsersScope
    @Provides
    fun userCash(db: Database): IUserCache {
        return RoomUserCache(db)
    }
}