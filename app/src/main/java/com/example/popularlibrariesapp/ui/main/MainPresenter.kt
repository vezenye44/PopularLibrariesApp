package com.example.popularlibrariesapp.ui.main

import com.example.popularlibrariesapp.ui.interfaces.navigate.IScreens
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class MainPresenter(val router: Router, val screens: IScreens) :
    MvpPresenter<MainContract.View>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}