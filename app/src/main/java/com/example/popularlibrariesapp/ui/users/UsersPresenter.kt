package com.example.popularlibrariesapp.ui.users

import com.example.popularlibrariesapp.domain.dto.GithubUser
import com.example.popularlibrariesapp.domain.repo.GithubUsersRepo
import com.example.popularlibrariesapp.ui.interfaces.navigate.IScreens
import com.example.popularlibrariesapp.ui.users.rv.IUserListPresenter
import com.example.popularlibrariesapp.ui.users.rv.UserItemView
import com.github.terrakok.cicerone.Router
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

    private fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
