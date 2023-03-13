package com.example.popularlibrariesapp

interface CounterContract {
    interface View {
        fun showMeaningFirstCounter(meaning: Int)
        fun showMeaningSecondCounter(meaning: Int)
        fun showMeaningThirdCounter(meaning: Int)
    }

    interface Presenter {
        fun onFirstCounterClick()
        fun onSecondCounterClick()
        fun onThirdCounterClick()
    }
}