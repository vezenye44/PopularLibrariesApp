package com.example.popularlibrariesapp.ui.users.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibrariesapp.R
import com.example.popularlibrariesapp.databinding.UsersRecyclerItemBinding
import com.example.popularlibrariesapp.domain.remote.image_loaders.IImageLoader

class UserViewHolder(
    parent: ViewGroup,
    private val imageLoader: IImageLoader<ImageView>
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.users_recycler_item, parent, false)
), UserItemView {
    private val binding = UsersRecyclerItemBinding.bind(itemView)

    override var pos = -1
    override fun setLogin(login: String) = with(binding) {
        usersRecyclerItemLoginTextview.text = login
    }

    override fun setId(id: String) {
        binding.usersRecyclerItemIdTextview.text = id
    }

    override fun loadAvatar(url: String) {
        imageLoader.loadInto(url, binding.usersRecyclerItemAvatarImageview)
    }

}
