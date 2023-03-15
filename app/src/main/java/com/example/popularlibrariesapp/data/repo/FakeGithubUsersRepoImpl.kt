package com.example.popularlibrariesapp.data.repo

import com.example.popularlibrariesapp.domain.dto.GithubUser
import com.example.popularlibrariesapp.domain.repo.GithubUsersRepo
import io.reactivex.rxjava3.core.Observable

class FakeGithubUsersRepoImpl: GithubUsersRepo {

    private val repositories = listOf(
        GithubUser("login1"),
        GithubUser("login2"),
        GithubUser("login3"),
        GithubUser("login4"),
        GithubUser("login5")
    )

    override fun getUsers() : List<GithubUser> = repositories
    override fun getUsersByRx(): Observable<List<GithubUser>> = Observable.just(repositories)

}