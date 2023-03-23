package com.example.popularlibrariesapp.ui.userrepoinfo

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class UserRepositoryInfoPresenter(private val forkCount: String, private val router: Router) :
    MvpPresenter<UserRepositoryInfoContract.View>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.showForkCount(forkCount)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
