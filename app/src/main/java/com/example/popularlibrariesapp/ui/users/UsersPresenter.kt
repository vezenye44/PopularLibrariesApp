package com.example.popularlibrariesapp.ui.users

import com.example.popularlibrariesapp.domain.dto.GithubUser
import com.example.popularlibrariesapp.domain.repo.GithubUsersRepo
import com.example.popularlibrariesapp.ui.interfaces.navigate.IScreens
import com.example.popularlibrariesapp.ui.users.rv.IUserListPresenter
import com.example.popularlibrariesapp.ui.users.rv.UserItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter

class UsersPresenter(
    private val usersRepo: GithubUsersRepo,
    private val router: Router,
    private val screens: IScreens
) :
    MvpPresenter<UsersContract.View>() {
    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            view.setLogin(user.login)
        }
    }

    val usersListPresenter = UsersListPresenter()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            val login = usersListPresenter.users[itemView.pos].login
            router.navigateTo(screens.userProfile(login), true)
        }
    }

    private lateinit var disposable: Disposable
    private fun loadData() {
        disposable = usersRepo
            .getUsersByRx()
            .switchMap {
                return@switchMap Observable.just(it)
            }
            .subscribeBy(
            onNext = {
                usersListPresenter.users.addAll(it)
                viewState.updateList()
            }
        )
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
