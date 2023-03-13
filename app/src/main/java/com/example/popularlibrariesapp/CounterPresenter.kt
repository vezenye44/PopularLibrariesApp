package com.example.popularlibrariesapp

class CounterPresenter(private val view: CounterContract.View) {
    private val model = CounterModel()

    // TODO : Исправить реализацию
    fun counterClick(id: Int) {
        when (id) {
            R.id.main_activity_counter_one_btn -> {
                val nextValue = model.next(0)
                view.setButtonText(0, nextValue.toString())
            }
            R.id.main_activity_counter_two_btn -> {
                val nextValue = model.next(1)
                view.setButtonText(1, nextValue.toString())
            }
            R.id.main_activity_counter_three_btn -> {
                val nextValue = model.next(2)
                view.setButtonText(2, nextValue.toString())
            }
        }
    }
}