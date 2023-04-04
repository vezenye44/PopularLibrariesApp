package com.example.popularlibrariesapp.ui.users.rv

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibrariesapp.domain.image_loaders.IImageLoader

class UsersAdapter(
    private val presenter: IUserListPresenter,
    private val imageLoader: IImageLoader<ImageView>
) : RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(parent, imageLoader).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        return presenter.bindView(holder.apply { pos = position })
    }

}