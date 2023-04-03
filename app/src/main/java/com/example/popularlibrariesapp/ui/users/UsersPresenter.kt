package com.example.popularlibrariesapp.ui.users

import com.example.popularlibrariesapp.domain.dto.GithubUserEntity
import com.example.popularlibrariesapp.domain.repo.GithubUsersRepo
import com.example.popularlibrariesapp.ui.interfaces.navigate.IScreens
import com.example.popularlibrariesapp.ui.users.rv.IUserListPresenter
import com.example.popularlibrariesapp.ui.users.rv.UserItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import javax.inject.Inject

class UsersPresenter() :
    MvpPresenter<UsersContract.View>() {

    @Inject
    lateinit var usersRepo: GithubUsersRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUserEntity>()
        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.id?.let { view.setId(it) }
            user.avatarUrl?.let { view.loadAvatar(it) }
        }
    }

    val usersListPresenter = UsersListPresenter()
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            val login = usersListPresenter.users[itemView.pos].login
            login?.let {
                router.navigateTo(screens.userProfile(it), true)
            }
        }
    }

    private lateinit var disposable: Disposable
    private fun loadData() {
        disposable = usersRepo
            .getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    usersListPresenter.users.addAll(it)
                    viewState.updateList()
                },
                onError = {
                    it.message?.let { errorMessage ->
                        viewState.showToast(errorMessage)
                    }
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
