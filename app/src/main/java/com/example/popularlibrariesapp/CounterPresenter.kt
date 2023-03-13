package com.example.popularlibrariesapp

class CounterPresenter(
    private val view: CounterContract.View
) : CounterContract.Presenter {

    private val model = CounterModel()

    override fun onFirstCounterClick() {
        val nextValue = model.next(0)
        view.showMeaningFirstCounter(nextValue)
    }

    override fun onSecondCounterClick() {
        val nextValue = model.next(1)
        view.showMeaningSecondCounter(nextValue)
    }

    override fun onThirdCounterClick() {
        val nextValue = model.next(2)
        view.showMeaningThirdCounter(nextValue)
    }
}