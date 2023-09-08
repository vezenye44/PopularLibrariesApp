package com.example.popularlibrariesapp.ui.userprofile

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.popularlibrariesapp.App
import com.example.popularlibrariesapp.databinding.FragmentUserProfileBinding
import com.example.popularlibrariesapp.ui.base.navigate.BackButtonListener
import com.example.popularlibrariesapp.ui.userprofile.rv.UserReposAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserProfileFragment : MvpAppCompatFragment(), UsesProfileContract.View, BackButtonListener {

    private var adapter: UserReposAdapter? = null
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!

    private val presenter: UserProfilePresenter by moxyPresenter {
        val login = arguments?.getString(EXTRA_USER_LOGIN) ?: "Fail"
        App.instance.initUserReposSubcomponent()
        val subcomponent = App.instance.userReposSubcomponent
        subcomponent!!.userProfilePresenterFactory().create(login, AndroidSchedulers.mainThread())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun init() {
        binding.fragmentUserProfileReposRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = UserReposAdapter(presenter.userRepoListPresenter)
        binding.fragmentUserProfileReposRecyclerView.adapter = adapter
    }

    override fun disableInjection() {
        App.instance.disableUserReposSubcomponent()
    }


    @SuppressLint("NotifyDataSetChanged")
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