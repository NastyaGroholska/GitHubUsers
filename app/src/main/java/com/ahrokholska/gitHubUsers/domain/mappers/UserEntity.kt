package com.ahrokholska.gitHubUsers.domain.mappers

import com.ahrokholska.gitHubUsers.data.local.users.UserEntity
import com.ahrokholska.gitHubUsers.presentation.model.User

fun UserEntity.toUser() = User(login = login, pictureURL = pictureURL)