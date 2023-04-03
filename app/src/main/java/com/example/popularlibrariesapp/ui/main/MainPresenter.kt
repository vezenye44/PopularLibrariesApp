package com.example.popularlibrariesapp.ui.main

import com.example.popularlibrariesapp.ui.base.navigate.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class MainPresenter() :
    MvpPresenter<MainContract.View>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}