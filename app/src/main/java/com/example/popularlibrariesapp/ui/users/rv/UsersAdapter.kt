package com.example.popularlibrariesapp.ui.users.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UsersAdapter(private val presenter: IUserListPresenter) :
    RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(parent).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        return presenter.bindView(holder.apply { pos = position })
    }

}