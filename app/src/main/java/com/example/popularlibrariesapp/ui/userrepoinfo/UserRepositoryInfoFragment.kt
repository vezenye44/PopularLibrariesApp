package com.example.popularlibrariesapp.ui.userrepoinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.popularlibrariesapp.App
import com.example.popularlibrariesapp.databinding.FragmentUserRepoInfoBinding
import com.example.popularlibrariesapp.ui.interfaces.navigate.BackButtonListener
import com.example.popularlibrariesapp.ui.userprofile.UserProfileFragment
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserRepositoryInfoFragment() : MvpAppCompatFragment(), UserRepositoryInfoContract.View,
    BackButtonListener {

    private var _binding: FragmentUserRepoInfoBinding? = null
    private val binding: FragmentUserRepoInfoBinding
        get() = _binding!!

    private val presenter: UserRepositoryInfoPresenter by moxyPresenter {
        val forkCount = arguments?.getString(EXTRA_FORK_COUNT) ?: "Fail"
        return@moxyPresenter UserRepositoryInfoPresenter(forkCount, App.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserRepoInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun showForkCount(forkCount: String) {
        binding.fragmentUserRepoInfoForkCountsTextView.text = forkCount
    }

    companion object {
        private const val EXTRA_FORK_COUNT = "EXTRA_FORK_COUNT"
        fun newInstance(forkCount: String) =
            UserRepositoryInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_FORK_COUNT, forkCount)
                }
            }

    }

    override fun backPressed() = presenter.backPressed()
}