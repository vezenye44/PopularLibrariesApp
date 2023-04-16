package com.example.popularlibrariesapp.dependency_injection.modules

import com.example.popularlibrariesapp.data.datacash.RoomUserRepositoriesCache
import com.example.popularlibrariesapp.data.datacash.room.Database
import com.example.popularlibrariesapp.data.remote.repo.CashedRetrofitGithubUserReposRepoImpl
import com.example.popularlibrariesapp.data.remote.retrofit.GithubApi
import com.example.popularlibrariesapp.dependency_injection.scopes.UserReposScope
import com.example.popularlibrariesapp.domain.datacash.IUserRepositoriesCache
import com.example.popularlibrariesapp.domain.remote.INetworkStatus
import com.example.popularlibrariesapp.domain.remote.repo.GithubUserReposRepo
import dagger.Module
import dagger.Provides

@Module
class UserReposModule {
    @UserReposScope
    @Provides
    fun reposRepo(
        api: GithubApi, networkStatus: INetworkStatus, reposCash: IUserRepositoriesCache
    ): GithubUserReposRepo {
        return CashedRetrofitGithubUserReposRepoImpl(api, networkStatus, reposCash)
    }

    @UserReposScope
    @Provides
    fun reposCash(db: Database): IUserRepositoriesCache {
        return RoomUserRepositoriesCache(db)
    }
}