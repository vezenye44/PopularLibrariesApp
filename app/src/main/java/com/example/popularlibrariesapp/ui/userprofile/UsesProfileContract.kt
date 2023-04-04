package com.example.popularlibrariesapp.ui.userprofile

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface UsesProfileContract {

    @StateStrategyType(AddToEndSingleStrategy::class)
    interface View : MvpView {

        fun init()
        fun updateList()
        fun showLogin(login: String)
        fun showToast(message: String)
    }
}