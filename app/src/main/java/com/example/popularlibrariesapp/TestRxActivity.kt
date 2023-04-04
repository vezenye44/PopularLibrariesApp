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
        fun createJust() = Observable.just(1, 2, 3, 4)
    }
    class Consumer(private val producer: Producer) {
        @SuppressLint("CheckResult")
        fun execFlatMap() {
            producer.createJust()
                .switchMap {
                    val delay = Random.nextInt(60_000).toLong()
                    val delayByIt = (4000/it).toLong()
                        .also { delay ->
                            println("delay: $delay & it: $it")
                        }
                        return@switchMap Observable
                        //.range(1,200)
                        .just(it.toString() + "x")
                        .delay(delayByIt,
                        TimeUnit.MILLISECONDS)
                }
                .subscribe({ s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                }, {
                    println("onComplete")
                })
        }
    }
}
