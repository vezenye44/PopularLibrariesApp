package com.example.popularlibrariesapp

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

interface CounterContract {
    @StateStrategyType(AddToEndSingleStrategy::class)
    interface View : MvpView {
        fun showMeaningFirstCounter(meaning: String)
        fun showMeaningSecondCounter(meaning: String)
        fun showMeaningThirdCounter(meaning: String)
    }

    /*interface Presenter {
        fun onFirstCounterClick()
        fun onSecondCounterClick()
        fun onThirdCounterClick()
    }*/
}