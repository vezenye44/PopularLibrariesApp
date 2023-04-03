package com.example.popularlibrariesapp.ui.userrepoinfo

import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import moxy.MvpPresenter

@AssistedFactory
interface UserRepositoryInfoPresenterFactory {
    fun create(forkCount: String): UserRepositoryInfoPresenter
}

class UserRepositoryInfoPresenter @AssistedInject constructor(
    @Assisted private val forkCount: String,
    private val router: Router,
) :
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
