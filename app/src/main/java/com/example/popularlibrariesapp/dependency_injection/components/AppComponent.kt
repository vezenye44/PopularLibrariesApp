package com.example.popularlibrariesapp.dependency_injection.components

import com.example.popularlibrariesapp.dependency_injection.components.subcomponents.UsersSubcomponent
import com.example.popularlibrariesapp.dependency_injection.modules.AppModule
import com.example.popularlibrariesapp.dependency_injection.modules.CiceroneModule
import com.example.popularlibrariesapp.dependency_injection.modules.DatabaseModule
import com.example.popularlibrariesapp.dependency_injection.modules.RemoteModule
import com.example.popularlibrariesapp.ui.main.MainActivity
import com.example.popularlibrariesapp.ui.main.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DatabaseModule::class,
        CiceroneModule::class,
        RemoteModule::class,
    ]
)
interface AppComponent {
    fun usersSubcomponent(): UsersSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)

}