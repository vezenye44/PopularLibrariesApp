package com.example.popularlibrariesapp

import android.os.Bundle
import com.example.popularlibrariesapp.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), CounterContract.View {

    private lateinit var binding: ActivityMainBinding
    private val presenter by moxyPresenter { CounterPresenter(CounterModel()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.mainActivityCounterOneBtn.setOnClickListener {
            presenter.onFirstCounterClick()
        }
        binding.mainActivityCounterTwoBtn.setOnClickListener {
            presenter.onSecondCounterClick()
        }
        binding.mainActivityCounterThreeBtn.setOnClickListener {
            presenter.onThirdCounterClick()
        }
    }

    override fun showMeaningFirstCounter(meaning: String) {
        binding.mainActivityCounterOneBtn.text = meaning
    }

    override fun showMeaningSecondCounter(meaning: String) {
        binding.mainActivityCounterTwoBtn.text = meaning
    }

    override fun showMeaningThirdCounter(meaning: String) {
        binding.mainActivityCounterThreeBtn.text = meaning
    }

}
