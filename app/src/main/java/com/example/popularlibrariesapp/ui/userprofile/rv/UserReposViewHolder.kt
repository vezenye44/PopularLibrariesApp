package com.example.popularlibrariesapp.ui.userprofile.rv

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibrariesapp.R
import com.example.popularlibrariesapp.databinding.UserReposRecyclerItemBinding

class UserReposViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), UserRepoItemView {
    private val binding = UserReposRecyclerItemBinding.bind(itemView)

    companion object {
        fun createView(parent: ViewGroup): UserReposViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.user_repos_recycler_item, parent, false)
            return UserReposViewHolder(view)
        }
    }

    override fun setName(name: String) {
        binding.userReposRecyclerItemNameTextView.text = name
    }

    override fun setId(id: String) {
        binding.userReposRecyclerItemIdTextView.text = id
    }

    override var pos = -1
}
