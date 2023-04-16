package com.example.popularlibrariesapp.ui.users

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface UsersContract {
    @StateStrategyType(AddToEndSingleStrategy::class)
    interface View : MvpView {
        fun init()
        fun disableInjection()
        fun updateList()
        fun showToast(message: String)
    }
}