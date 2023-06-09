package com.example.popularlibrariesapp.ui.base.navigate

import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun userProfile(userLogin: String): Screen
    fun userRepoInfo(forksCount: String): Screen
}