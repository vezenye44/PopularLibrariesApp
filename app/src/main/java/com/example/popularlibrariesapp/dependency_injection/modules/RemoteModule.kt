package com.example.popularlibrariesapp.dependency_injection.modules

import android.content.Context
import android.widget.ImageView
import com.example.popularlibrariesapp.App
import com.example.popularlibrariesapp.data.image_loaders.GlideImageLoader
import com.example.popularlibrariesapp.data.internet.AndroidNetworkStatus
import com.example.popularlibrariesapp.data.repo.CashedRetrofitGithubUserReposRepoImpl
import com.example.popularlibrariesapp.data.repo.CashedRetrofitGithubUsersRepoImpl
import com.example.popularlibrariesapp.data.retrofit.GithubApi
import com.example.popularlibrariesapp.domain.datacash.IUserCache
import com.example.popularlibrariesapp.domain.datacash.IUserRepositoriesCache
import com.example.popularlibrariesapp.domain.image_loaders.IImageLoader
import com.example.popularlibrariesapp.domain.internet.INetworkStatus
import com.example.popularlibrariesapp.domain.repo.GithubUserReposRepo
import com.example.popularlibrariesapp.domain.repo.GithubUsersRepo
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

private const val BASE_URL = "BASE_URL"

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun networkStatus(app: App): INetworkStatus {
        return AndroidNetworkStatus(app)
    }

    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> {
        return GlideImageLoader()
    }

    @Named(BASE_URL)
    @Provides
    fun baseUrl(): String {
        return "https://api.github.com"
    }

    @Singleton
    @Provides
    fun gson(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun retrofitClient(@Named(BASE_URL) baseUrl: String, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun api(retrofitClient: Retrofit): GithubApi {
        return retrofitClient.create(GithubApi::class.java)
    }

    @Singleton
    @Provides
    fun userRepo(
        api: GithubApi, networkStatus: INetworkStatus, userCash: IUserCache
    ): GithubUsersRepo {
        return CashedRetrofitGithubUsersRepoImpl(api, networkStatus, userCash)
    }

    @Singleton
    @Provides
    fun reposRepo(
        api: GithubApi, networkStatus: INetworkStatus, reposCash: IUserRepositoriesCache
    ): GithubUserReposRepo {
        return CashedRetrofitGithubUserReposRepoImpl(api, networkStatus, reposCash)
    }
}