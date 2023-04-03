package com.example.popularlibrariesapp.ui.users.rv

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.popularlibrariesapp.domain.remote.image_loaders.IImageLoader
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

@AssistedFactory
interface UsersAdapterFactory {
    fun create(presenter: IUserListPresenter): UsersAdapter
}

class UsersAdapter @AssistedInject constructor(
    @Assisted private val presenter: IUserListPresenter,
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