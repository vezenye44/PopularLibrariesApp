package com.example.popularlibrariesapp.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibrariesapp.R
import com.example.popularlibrariesapp.databinding.ItemUserBinding

class UserViewHolder(
    parent: ViewGroup
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
), UserItemView {
    private val binding = ItemUserBinding.bind(itemView)

    override var pos = -1
    override fun setLogin(text: String) = with(binding) {
        itemViewLoginTextView.text = text
    }
}
