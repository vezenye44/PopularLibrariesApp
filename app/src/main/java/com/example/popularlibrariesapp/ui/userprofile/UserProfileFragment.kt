package com.example.popularlibrariesapp.ui.userprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibrariesapp.App
import com.example.popularlibrariesapp.data.repo.RetrofitGithubUserReposRepoImpl
import com.example.popularlibrariesapp.data.retrofit.RetrofitClient
import com.example.popularlibrariesapp.databinding.FragmentUserProfileBinding
import com.example.popularlibrariesapp.ui.interfaces.navigate.BackButtonListener
import com.example.popularlibrariesapp.ui.main.AndroidScreens
import com.example.popularlibrariesapp.ui.userprofile.rv.UserReposAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserProfileFragment : MvpAppCompatFragment(), UsesProfileContract.View, BackButtonListener {

    var adapter: UserReposAdapter? = null
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    private val presenter: UserProfilePresenter by moxyPresenter {
        val login = arguments?.getString(EXTRA_USER_LOGIN) ?: "Fail"
        UserProfilePresenter(
            login, RetrofitGithubUserReposRepoImpl(RetrofitClient().githubApi),
            App.instance.router,
            AndroidScreens()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun init() {
        binding.fragmentUserProfileReposRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = UserReposAdapter(presenter.userRepoListPresenter)
        binding.fragmentUserProfileReposRecyclerView.adapter = adapter
    }


    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun showLogin(login: String) {
        binding.fragmentUserProfileLoginTextView.text = login
    }

    override fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val EXTRA_USER_LOGIN = "login"
        fun newInstance(userLogin: String) =
            UserProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_USER_LOGIN, userLogin)
                }
            }

    }

    override fun backPressed() = presenter.backPressed()

}