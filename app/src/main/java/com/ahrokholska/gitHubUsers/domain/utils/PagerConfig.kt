package com.ahrokholska.gitHubUsers.domain.utils

import androidx.paging.PagingConfig

object PagerConfig {
    fun getDefaultConfig() = PagingConfig(
        pageSize = 30,
        prefetchDistance = 0,
        initialLoadSize = 30,
        enablePlaceholders = true
    )
}