package com.example.popularlibrariesapp.domain.dto

import com.google.gson.annotations.Expose

data class GithubUserReposEntity(
    @Expose val name: String? = null,
    @Expose val id: String? = null,
    @Expose val forksCount: String? = null,
    @Expose val userId: String? = null
)