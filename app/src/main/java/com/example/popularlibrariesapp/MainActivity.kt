package com.example.popularlibrariesapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.popularlibrariesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), CounterContract.View {

    private lateinit var binding: ActivityMainBinding
    private val presenter = CounterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        val listener = View.OnClickListener {
            presenter.counterClick(it.id)
        }

        binding.mainActivityCounterOneBtn.setOnClickListener(listener)
        binding.mainActivityCounterTwoBtn.setOnClickListener(listener)
        binding.mainActivityCounterThreeBtn.setOnClickListener(listener)
    }

    override fun setButtonText(index: Int, text: String) {
        when(index){
            0 -> binding.mainActivityCounterOneBtn.text = text
            1 -> binding.mainActivityCounterTwoBtn.text = text
            2 -> binding.mainActivityCounterThreeBtn.text = text
        }
    }
}
