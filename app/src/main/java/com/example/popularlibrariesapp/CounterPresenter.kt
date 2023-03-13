package com.example.popularlibrariesapp

import moxy.MvpPresenter

class CounterPresenter(
    private val model: CounterModel
) : MvpPresenter<CounterContract.View>() {

    fun onFirstCounterClick() {
        val nextValue = model.next(0)
        viewState.showMeaningFirstCounter(nextValue.toString())
    }

    fun onSecondCounterClick() {
        val nextValue = model.next(1)
        viewState.showMeaningSecondCounter(nextValue.toString())
    }

    fun onThirdCounterClick() {
        val nextValue = model.next(2)
        viewState.showMeaningThirdCounter(nextValue.toString())
    }
}