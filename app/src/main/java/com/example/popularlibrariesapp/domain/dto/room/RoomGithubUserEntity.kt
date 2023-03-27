package com.example.popularlibrariesapp.domain.dto.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoomGithubUserEntity(
    @PrimaryKey var id: String,
    var login: String,
    var avatarUrl: String,
    var reposUrl: String
)
