package com.ahrokholska.gitHubUsers.data.remote.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ahrokholska.gitHubUsers.data.local.UserDatabase
import com.ahrokholska.gitHubUsers.data.local.repositories.RepositoryEntity
import com.ahrokholska.gitHubUsers.domain.mappers.toRepositoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class RepositoryRemoteMediator(
    private val repositoryRemote: RepositoryAPI,
    private val repositoryLocal: UserDatabase,
    private val userName: String
) : RemoteMediator<Int, RepositoryEntity>() {
    override suspend fun initialize(): InitializeAction = withContext(Dispatchers.IO) {
        if (repositoryLocal.getRepositoryDao.isEmpty(userName)) InitializeAction.LAUNCH_INITIAL_REFRESH else InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RepositoryEntity>
    ): MediatorResult =
        withContext(Dispatchers.IO) {
            try {
                val loadKey = when (loadType) {
                    LoadType.REFRESH -> 1
                    LoadType.PREPEND ->
                        return@withContext MediatorResult.Success(endOfPaginationReached = false)

                    LoadType.APPEND -> repositoryLocal.getRepositoryDao.lastPage(userName) + 1
                }

                val response = repositoryRemote.getRepositoriesForUser(userName, loadKey)

                with(repositoryLocal) {
                    withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            getRepositoryDao.clearAll(userName)
                        }
                        getRepositoryDao.insertAll(response.map {
                            it.toRepositoryEntity(loadKey)
                        })
                    }
                }

                MediatorResult.Success(endOfPaginationReached = response.isEmpty())
            } catch (e: IOException) {
                MediatorResult.Error(e)
            } catch (e: HttpException) {
                MediatorResult.Error(e)
            }
        }
}

