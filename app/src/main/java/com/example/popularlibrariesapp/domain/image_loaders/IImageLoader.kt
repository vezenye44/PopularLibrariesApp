package com.example.popularlibrariesapp.domain.image_loaders

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}