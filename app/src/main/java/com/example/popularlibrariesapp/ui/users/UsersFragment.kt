package com.example.popularlibrariesapp.ui.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibrariesapp.App
import com.example.popularlibrariesapp.data.datacash.RoomUserCache
import com.example.popularlibrariesapp.data.image_loaders.GlideImageLoader
import com.example.popularlibrariesapp.data.repo.CashedRetrofitGithubUsersRepoImpl
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
        UsersPresenter(
            CashedRetrofitGithubUsersRepoImpl(
                api = RetrofitClient().githubApi,
                networkStatus = App.instance.networkStatus,
                userCash = RoomUserCache(App.instance.database)
            ),
            App.instance.router,
            AndroidScreens()
        )
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

    override fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun backPressed() = presenter.backPressed()
}
