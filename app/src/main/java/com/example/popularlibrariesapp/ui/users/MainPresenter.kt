package com.example.popularlibrariesapp.ui.users

import com.example.popularlibrariesapp.domain.dto.GithubUser
import com.example.popularlibrariesapp.domain.repo.GithubUsersRepo
import moxy.MvpPresenter

class MainPresenter(private val usersRepo: GithubUsersRepo) : MvpPresenter<UsersContract.View>() {
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
            //TODO: переход на экран пользователя
        }
    }

    fun loadData() {
        val users = usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }
}
