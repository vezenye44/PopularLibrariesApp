package com.example.popularlibrariesapp.ui.userprofile

import com.example.popularlibrariesapp.domain.dto.GithubUserReposEntity
import com.example.popularlibrariesapp.domain.remote.repo.GithubUserReposRepo
import com.example.popularlibrariesapp.ui.base.navigate.IScreens
import com.example.popularlibrariesapp.ui.userprofile.rv.IUserReposListPresenter
import com.example.popularlibrariesapp.ui.userprofile.rv.UserRepoItemView
import com.github.terrakok.cicerone.Router
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter

@AssistedFactory
interface UserProfilePresenterFactory {
    fun create(usersLogin: String, uiScheduler: Scheduler): UserProfilePresenter
}

class UserProfilePresenter @AssistedInject constructor(
    @Assisted private val usersLogin: String,
    @Assisted private val uiScheduler: Scheduler,
    private val repository: GithubUserReposRepo,
    private val router: Router,
    private val screens: IScreens
) : MvpPresenter<UsesProfileContract.View>() {

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
            val forksCount: String? = userRepoListPresenter.users[it.pos].forksCount
            forksCount?.let { router.navigateTo(screens.userRepoInfo(forksCount)) }
        }
    }

    private lateinit var disposable: Disposable
    private fun loadData() {
        disposable = repository
            .getUserRepos(usersLogin)
            .observeOn(uiScheduler)
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
        viewState.disableInjection()
        disposable.dispose()
        super.onDestroy()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}