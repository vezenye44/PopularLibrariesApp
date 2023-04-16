package com.example.popularlibrariesapp

import android.app.Application
import com.example.popularlibrariesapp.dependency_injection.components.AppComponent
import com.example.popularlibrariesapp.dependency_injection.components.DaggerAppComponent
import com.example.popularlibrariesapp.dependency_injection.components.subcomponents.UserReposSubcomponent
import com.example.popularlibrariesapp.dependency_injection.components.subcomponents.UsersSubcomponent
import com.example.popularlibrariesapp.dependency_injection.modules.AppModule

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    var usersSubcomponent: UsersSubcomponent? = null
        private set
    var userReposSubcomponent: UserReposSubcomponent? = null
        private set

    fun initUsersSubcomponent() = appComponent.usersSubcomponent().also {
        usersSubcomponent = it
    }

    fun disableUsersSubcomponent() {
        usersSubcomponent = null
    }

    fun initUserReposSubcomponent() = usersSubcomponent?.userReposSubcomponent().also {
        userReposSubcomponent = it
    }

    fun disableUserReposSubcomponent() {
        userReposSubcomponent = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}
