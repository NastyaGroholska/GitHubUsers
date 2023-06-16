package com.ahrokholska.gitHubUsers.presentation.ui.repositories

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.ahrokholska.gitHubUsers.data.local.repositories.RepositoryDao
import com.ahrokholska.gitHubUsers.data.remote.repositories.RepositoryRemoteMediator
import com.ahrokholska.gitHubUsers.domain.mappers.toRepository
import com.ahrokholska.gitHubUsers.domain.utils.PagerConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    remoteMediator: RepositoryRemoteMediator,
    private val repositoryLocal: RepositoryDao,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val userName: String = checkNotNull(savedStateHandle["userName"])

    @OptIn(ExperimentalPagingApi::class)
    val repositories = Pager(
        config = PagerConfig.getDefaultConfig(),
        remoteMediator = remoteMediator.apply { this.userName = this@RepositoryViewModel.userName },
        pagingSourceFactory = { repositoryLocal.pagingSource(userName) }
    ).flow.map { pagingData ->
        pagingData.map { entity ->
            entity.toRepository()
        }
    }.cachedIn(viewModelScope)
}