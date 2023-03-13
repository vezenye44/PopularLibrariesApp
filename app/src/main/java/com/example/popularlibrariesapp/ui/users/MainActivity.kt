package com.example.popularlibrariesapp.ui.users

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibrariesapp.data.repo.FakeGithubUsersRepoImpl
import com.example.popularlibrariesapp.databinding.ActivityMainBinding
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), UsersContract.View {

    private val presenter by moxyPresenter { MainPresenter(FakeGithubUsersRepoImpl()) }
    private var adapter: UsersAdapter? = null
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun init() {
        binding.mainActivityUsersRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UsersAdapter(presenter.usersListPresenter)
        binding.mainActivityUsersRecyclerView.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }
}
