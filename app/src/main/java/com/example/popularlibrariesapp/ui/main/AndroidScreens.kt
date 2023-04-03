package com.example.popularlibrariesapp.ui.main

import com.example.popularlibrariesapp.ui.base.navigate.IScreens
import com.example.popularlibrariesapp.ui.userprofile.UserProfileFragment
import com.example.popularlibrariesapp.ui.userrepoinfo.UserRepositoryInfoFragment
import com.example.popularlibrariesapp.ui.users.UsersFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun userProfile(userLogin: String) =
        FragmentScreen { UserProfileFragment.newInstance(userLogin) }

    override fun userRepoInfo(forksCount: String) =
        FragmentScreen { UserRepositoryInfoFragment.newInstance(forksCount) }
}