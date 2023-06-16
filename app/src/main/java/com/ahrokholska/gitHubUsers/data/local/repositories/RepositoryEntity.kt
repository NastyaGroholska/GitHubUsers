package com.ahrokholska.gitHubUsers.data.local.repositories

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.ahrokholska.gitHubUsers.data.local.users.UserEntity

@Entity(
    tableName = "repositories",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["login"],
            childColumns = ["owner"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RepositoryEntity(
    @PrimaryKey val id: Int,
    val owner: String,
    val page: Int,
    val name: String,
    val description: String?,
    val createdAt: String
)