package com.example.popularlibrariesapp.ui.main

import com.example.popularlibrariesapp.ui.interfaces.navigate.IScreens
import com.example.popularlibrariesapp.ui.userprofile.UserProfileFragment
import com.example.popularlibrariesapp.ui.users.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun userProfile(userLogin: String) = FragmentScreen {UserProfileFragment.newInstance(userLogin)}
}