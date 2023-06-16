package com.ahrokholska.gitHubUsers.data.local.users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(@PrimaryKey val id: Int, val login: String, val pictureURL: String)