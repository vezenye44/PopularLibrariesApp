package com.example.popularlibrariesapp.domain.dto.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = RoomGithubUserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RoomGithubUserReposEntity(
    @PrimaryKey var id: String,
    var name: String,
    var forksCount: String,
    var userId: String
)