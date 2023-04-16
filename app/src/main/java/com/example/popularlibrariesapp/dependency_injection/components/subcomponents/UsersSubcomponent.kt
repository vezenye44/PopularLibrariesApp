package com.example.popularlibrariesapp.dependency_injection.components.subcomponents

import com.example.popularlibrariesapp.dependency_injection.modules.UsersModule
import com.example.popularlibrariesapp.dependency_injection.scopes.UsersScope
import com.example.popularlibrariesapp.ui.users.UsersPresenterFactory
import com.example.popularlibrariesapp.ui.users.rv.UsersAdapterFactory
import dagger.Subcomponent

@UsersScope
@Subcomponent(
    modules = [
        UsersModule::class,
    ]
)
interface UsersSubcomponent {
    fun userReposSubcomponent(): UserReposSubcomponent

    fun usersAdapterFactory(): UsersAdapterFactory
    fun usersPresenterFactory(): UsersPresenterFactory
}