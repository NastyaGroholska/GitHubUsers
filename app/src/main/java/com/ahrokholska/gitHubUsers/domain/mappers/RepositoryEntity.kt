package com.ahrokholska.gitHubUsers.domain.mappers

import com.ahrokholska.gitHubUsers.data.local.repositories.RepositoryEntity
import com.ahrokholska.gitHubUsers.presentation.model.Repository

fun RepositoryEntity.toRepository() = Repository(
    name = name,
    description = description,
    createdAt = createdAt
)