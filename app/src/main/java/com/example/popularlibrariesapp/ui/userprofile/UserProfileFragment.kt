package com.example.popularlibrariesapp.ui.userprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.popularlibrariesapp.App
import com.example.popularlibrariesapp.databinding.FragmentUserProfileBinding
import com.example.popularlibrariesapp.ui.interfaces.navigate.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserProfileFragment : MvpAppCompatFragment(), UsesProfileContract.View, BackButtonListener {

    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    private val presenter: UserProfilePresenter by moxyPresenter {
        val login = arguments?.getString(EXTRA_USER_LOGIN) ?: "Fail"
        UserProfilePresenter(login, App.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun showLogin(login: String) {
        binding.fragmentUserProfileLoginTextview.text = login
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