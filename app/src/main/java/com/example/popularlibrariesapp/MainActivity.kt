package com.example.popularlibrariesapp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.popularlibrariesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var counters = mutableListOf(0, 0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        setCounters()

        binding.mainActivityCounterOneBtn.setOnClickListener {
            binding.mainActivityCounterOneBtn.text = (++counters[0]).toString()
        }
        binding.mainActivityCounterTwoBtn.setOnClickListener {
            binding.mainActivityCounterTwoBtn.text = (++counters[1]).toString()
        }
        binding.mainActivityCounterThreeBtn.setOnClickListener {
            binding.mainActivityCounterThreeBtn.text = (++counters[2]).toString()
        }
    }

    private fun setCounters() {
        binding.mainActivityCounterOneBtn.text = counters[0].toString()
        binding.mainActivityCounterTwoBtn.text = counters[1].toString()
        binding.mainActivityCounterThreeBtn.text = counters[2].toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putIntArray(EXTRA_COUNTERS, counters.toIntArray())
    }

    override fun onSaveInstanceState(
        outState: Bundle, outPersistentState:
        PersistableBundle
    ) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putIntArray(EXTRA_COUNTERS, counters.toIntArray())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val countersArray = savedInstanceState.getIntArray(EXTRA_COUNTERS)
        countersArray?.toList()?.let {
            counters.clear()
            counters.addAll(it)
        }
        setCounters()
    }

    companion object {
        private const val EXTRA_COUNTERS = "counters"
    }
}
