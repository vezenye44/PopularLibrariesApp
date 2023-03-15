package com.example.popularlibrariesapp.ui.userprofile

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserProfilePresenter(private val usersLogin: String, private val router: Router) :
    MvpPresenter<UsesProfileContract.View>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showLogin(usersLogin)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}