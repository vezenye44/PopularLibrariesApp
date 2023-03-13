package com.example.popularlibrariesapp

interface CounterContract {
    interface View {
        fun setButtonText(index: Int, text: String)
    }
}