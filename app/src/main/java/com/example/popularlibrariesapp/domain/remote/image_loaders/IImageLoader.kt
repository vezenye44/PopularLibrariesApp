package com.example.popularlibrariesapp.domain.remote.image_loaders

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}