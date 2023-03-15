package com.example.popularlibrariesapp.domain.repo

import com.example.popularlibrariesapp.domain.dto.GithubUser
import io.reactivex.rxjava3.core.Observable

interface GithubUsersRepo {
    fun getUsers() : List<GithubUser>

    fun getUsersByRx() : Observable<List<GithubUser>>
}