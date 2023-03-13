package com.example.popularlibrariesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.popularlibrariesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CounterContract.View {

    private lateinit var binding: ActivityMainBinding
    private val presenter: CounterContract.Presenter = CounterPresenter(this)

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

    override fun showMeaningFirstCounter(meaning: Int) {
        binding.mainActivityCounterOneBtn.text = meaning.toString()
    }

    override fun showMeaningSecondCounter(meaning: Int) {
        binding.mainActivityCounterTwoBtn.text = meaning.toString()
    }

    override fun showMeaningThirdCounter(meaning: Int) {
        binding.mainActivityCounterThreeBtn.text = meaning.toString()
    }

}
