package com.example.popularlibrariesapp.domain.dto

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubUserEntity(
    @Expose val login: String? = null,
    @Expose val id: String? = null,
    @Expose val avatarUrl: String? = null,
    @Expose val reposUrl: String? = null
) : Parcelable