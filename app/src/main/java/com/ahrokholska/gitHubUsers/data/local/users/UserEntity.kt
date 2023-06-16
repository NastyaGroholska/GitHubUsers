package com.ahrokholska.gitHubUsers.data.local.users

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(@PrimaryKey val login: String, val id: Int, val pictureURL: String)