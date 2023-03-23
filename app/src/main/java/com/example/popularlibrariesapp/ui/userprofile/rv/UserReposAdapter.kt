package com.example.popularlibrariesapp.ui.userprofile.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UserReposAdapter(private val presenter: IUserReposListPresenter) :
    RecyclerView.Adapter<UserReposViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserReposViewHolder.createView(parent)

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: UserReposViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
        holder.itemView.setOnClickListener {
            presenter.itemClickListener?.invoke(holder)
        }
    }
}