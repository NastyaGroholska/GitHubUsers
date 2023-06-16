package com.ahrokholska.gitHubUsers.presentation.ui.repositories

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.ahrokholska.gitHubUsers.data.local.UserDatabase
import com.ahrokholska.gitHubUsers.data.remote.repositories.RepositoryAPI
import com.ahrokholska.gitHubUsers.data.remote.repositories.RepositoryRemoteMediator
import com.ahrokholska.gitHubUsers.domain.mappers.toRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    repositoryRemote: RepositoryAPI,
    private val repositoryLocal: UserDatabase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val userName: String = checkNotNull(savedStateHandle["userName"])

    @OptIn(ExperimentalPagingApi::class)
    val repositories = Pager(
        config = PagingConfig(
            pageSize = 30,
            prefetchDistance = 0,
            initialLoadSize = 30,
            enablePlaceholders = true
        ),
        remoteMediator = RepositoryRemoteMediator(repositoryRemote, repositoryLocal, userName),
        pagingSourceFactory = {
            repositoryLocal.getRepositoryDao.pagingSource(userName)
        }
    ).flow.map { pagingData ->
        pagingData.map { entity ->
            entity.toRepository()
        }
    }.cachedIn(viewModelScope)
}