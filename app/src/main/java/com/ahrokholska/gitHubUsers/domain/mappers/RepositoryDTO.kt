package com.ahrokholska.gitHubUsers.domain.mappers

import com.ahrokholska.gitHubUsers.data.local.repositories.RepositoryEntity
import com.ahrokholska.gitHubUsers.data.remote.repositories.RepositoryDTO
import com.ahrokholska.gitHubUsers.domain.utils.Parser

fun RepositoryDTO.toRepositoryEntity(page: Int) = RepositoryEntity(
    id = id,
    page = page,
    owner = owner?.login.orEmpty(),
    name = name,
    description = description,
    createdAt = Parser.zonedDateTimeToDate(createdAt.orEmpty())
)