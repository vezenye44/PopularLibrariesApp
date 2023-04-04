package com.example.popularlibrariesapp.ui.userrepoinfo

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface UserRepositoryInfoContract {
    @StateStrategyType(AddToEndSingleStrategy::class)
    interface View: MvpView {
        fun showForkCount(forkCount: String)
    }
}