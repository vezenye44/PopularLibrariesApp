package com.example.popularlibrariesapp.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibrariesapp.App
import com.example.popularlibrariesapp.data.image_loaders.GlideImageLoader
import com.example.popularlibrariesapp.data.repo.FakeGithubUsersRepoImpl
import com.example.popularlibrariesapp.data.repo.RetrofitGithubUsersRepoImpl
import com.example.popularlibrariesapp.data.retrofit.GithubApi
import com.example.popularlibrariesapp.data.retrofit.RetrofitClient
import com.example.popularlibrariesapp.databinding.FragmentUsersBinding
import com.example.popularlibrariesapp.ui.interfaces.navigate.BackButtonListener
import com.example.popularlibrariesapp.ui.main.AndroidScreens
import com.example.popularlibrariesapp.ui.users.rv.UsersAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersContract.View, BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(RetrofitGithubUsersRepoImpl(RetrofitClient().githubApi), App.instance.router, AndroidScreens())
    }
    var adapter: UsersAdapter? = null
    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun init() {
        binding.usersFragmentRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = UsersAdapter(presenter.usersListPresenter, GlideImageLoader())
        binding.usersFragmentRecyclerView.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed() = presenter.backPressed()
}
