package com.example.popularlibrariesapp.data.remote.image_loaders

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.popularlibrariesapp.domain.remote.image_loaders.IImageLoader

class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .into(container)
    }
}
