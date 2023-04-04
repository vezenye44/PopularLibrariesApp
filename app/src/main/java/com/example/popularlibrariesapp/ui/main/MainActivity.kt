package com.example.popularlibrariesapp.ui.main

import android.os.Bundle
import com.example.popularlibrariesapp.App
import com.example.popularlibrariesapp.R
import com.example.popularlibrariesapp.databinding.ActivityMainBinding
import com.example.popularlibrariesapp.ui.interfaces.navigate.BackButtonListener
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainContract.View {
    val navigator = AppNavigator(this, R.id.main_activity_container)
    private val presenter by moxyPresenter {
        MainPresenter(
            App.instance.router,
            AndroidScreens()
        )
    }

    private var vb: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClicked()
    }
}

