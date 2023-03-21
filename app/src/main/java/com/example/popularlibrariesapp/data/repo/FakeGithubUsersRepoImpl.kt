package com.example.popularlibrariesapp.data.repo

import com.example.popularlibrariesapp.domain.dto.GithubUserEntity
import com.example.popularlibrariesapp.domain.repo.GithubUsersRepo
import io.reactivex.rxjava3.core.Single

class FakeGithubUsersRepoImpl : GithubUsersRepo {

    private val repositories = listOf(
        GithubUserEntity("mojombo", "1", "https://avatars.githubusercontent.com/u/1?v=4"),
        GithubUserEntity("defunkt", "2", "https://avatars.githubusercontent.com/u/2?v=4"),
        GithubUserEntity("pjhyett", "3", "https://avatars.githubusercontent.com/u/3?v=4")
    )

    override fun getUsers() : Single<List<GithubUserEntity>> = Single.just(repositories)

}