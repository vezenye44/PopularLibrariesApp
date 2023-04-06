package com.example.popularlibrariesapp.ui.users.rv

import com.example.popularlibrariesapp.ui.base.list.IItemView

interface UserItemView : IItemView {

    fun setLogin(login: String)
    fun setId(id: String)
    fun loadAvatar(url: String)
}
