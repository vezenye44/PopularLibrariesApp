package com.example.popularlibrariesapp.dependency_injection.components.subcomponents

import com.example.popularlibrariesapp.dependency_injection.modules.UserReposModule
import com.example.popularlibrariesapp.dependency_injection.scopes.UserReposScope
import com.example.popularlibrariesapp.ui.userprofile.UserProfilePresenterFactory
import com.example.popularlibrariesapp.ui.userrepoinfo.UserRepositoryInfoPresenterFactory
import dagger.Subcomponent

@UserReposScope
@Subcomponent(
    modules = [
        UserReposModule::class
    ]
)
interface UserReposSubcomponent {
    fun userProfilePresenterFactory(): UserProfilePresenterFactory
    fun userRepositoryInfoPresenterFactory(): UserRepositoryInfoPresenterFactory
}