package com.example.popularlibrariesapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class TestRxActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_rx)


        Operators().exec()
    }
}

class Operators {
    fun exec() {
        Consumer(Producer()).execFlatMap()
    }
    class Producer {
        fun createJust() = Observable.just("1", "2", "3", "3")
    }
    class Consumer(private val producer: Producer) {
        @SuppressLint("CheckResult")
        fun execFlatMap() {
            producer.createJust()
                .switchMap {
                    val delay = Random.nextInt(1000).toLong()

                    return@switchMap Observable
                        //.range(1,200)
                        .just(it + "x")
                        .delay(delay,
                        TimeUnit.MILLISECONDS)
                }
                .subscribe({ s ->
                    Log.d("TAG", "onNext: $s")
                    println("onNext: $s")
                }, {
                    Log.d("TAG", "onNext: ${it.message}")
                    println("onError: ${it.message}")
                })
        }
    }
}
