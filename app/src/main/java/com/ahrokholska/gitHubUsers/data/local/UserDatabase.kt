package com.ahrokholska.gitHubUsers.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahrokholska.gitHubUsers.data.local.repositories.RepositoryDao
import com.ahrokholska.gitHubUsers.data.local.repositories.RepositoryEntity
import com.ahrokholska.gitHubUsers.data.local.users.UserDao
import com.ahrokholska.gitHubUsers.data.local.users.UserEntity

@Database(
    entities = [UserEntity::class, RepositoryEntity::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {
    abstract val getUserDao: UserDao
    abstract val getRepositoryDao: RepositoryDao
}