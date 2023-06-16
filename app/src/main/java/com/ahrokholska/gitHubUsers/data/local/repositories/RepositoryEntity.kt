package com.ahrokholska.gitHubUsers.data.local.repositories

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repositories")
data class RepositoryEntity(
    @PrimaryKey val id: Int,
    val owner: String,
    val page: Int,
    val name: String,
    val description: String?,
    val createdAt: String
)