package com.example.popularlibrariesapp.ui.userprofile.rv

import com.example.popularlibrariesapp.ui.interfaces.list.IItemView

interface UserRepoItemView : IItemView {
    fun setName(name: String)
    fun setId(id: String)
}
