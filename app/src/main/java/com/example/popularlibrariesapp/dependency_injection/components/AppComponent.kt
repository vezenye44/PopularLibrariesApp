package com.example.popularlibrariesapp.dependency_injection.components

import com.example.popularlibrariesapp.dependency_injection.modules.AppModule
import com.example.popularlibrariesapp.dependency_injection.modules.CashModule
import com.example.popularlibrariesapp.dependency_injection.modules.CiceroneModule
import com.example.popularlibrariesapp.dependency_injection.modules.RemoteModule
import com.example.popularlibrariesapp.ui.main.MainActivity
import com.example.popularlibrariesapp.ui.main.MainPresenter
import com.example.popularlibrariesapp.ui.userprofile.UserProfilePresenterFactory
import com.example.popularlibrariesapp.ui.userrepoinfo.UserRepositoryInfoPresenterFactory
import com.example.popularlibrariesapp.ui.users.UsersPresenterFactory
import com.example.popularlibrariesapp.ui.users.rv.UsersAdapterFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CashModule::class,
        CiceroneModule::class,
        RemoteModule::class,
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(mainPresenter: MainPresenter)

    fun usersAdapterFactory(): UsersAdapterFactory

    fun usersPresenterFactory(): UsersPresenterFactory

    fun userProfilePresenterFactory(): UserProfilePresenterFactory

    fun userRepositoryInfoPresenterFactory(): UserRepositoryInfoPresenterFactory
}