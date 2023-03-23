package com.example.popularlibrariesapp.ui.userprofile

import com.example.popularlibrariesapp.domain.dto.GithubUserReposEntity
import com.example.popularlibrariesapp.domain.repo.GithubUserReposRepo
import com.example.popularlibrariesapp.ui.interfaces.navigate.IScreens
import com.example.popularlibrariesapp.ui.userprofile.rv.IUserReposListPresenter
import com.example.popularlibrariesapp.ui.userprofile.rv.UserRepoItemView
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter

class UserProfilePresenter(
    private val usersLogin: String,
    private val repository: GithubUserReposRepo,
    private val router: Router,
    private val screens: IScreens
) :
    MvpPresenter<UsesProfileContract.View>() {

    class UserRepoListPresenter(
    ) : IUserReposListPresenter {
        override var itemClickListener: ((UserRepoItemView) -> Unit)? = null
        val users = mutableListOf<GithubUserReposEntity>()

        override fun bindView(view: UserRepoItemView) {
            val user = users[view.pos]
            user.name?.let { view.setName(it) }
            user.id?.let { view.setId("ID : $it") }
        }

        override fun getCount() = users.size
    }

    val userRepoListPresenter = UserRepoListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.showLogin(usersLogin)
        loadData()
        userRepoListPresenter.itemClickListener = {
           // router.navigateTo(screens.userRepoInfo())
        }
    }

    private lateinit var disposable: Disposable
    private fun loadData() {
        disposable = repository
            .getUserRepos(usersLogin)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    userRepoListPresenter.users.addAll(it)
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