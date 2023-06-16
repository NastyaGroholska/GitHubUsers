package com.ahrokholska.gitHubUsers.domain.mappers

import com.ahrokholska.gitHubUsers.data.local.users.UserEntity
import com.ahrokholska.gitHubUsers.data.remote.users.UserDTO

fun UserDTO.toUserEntity() = UserEntity(id = id, login = login, pictureURL = avatarURL)