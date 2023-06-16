package com.ahrokholska.gitHubUsers.data.remote.users

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.ahrokholska.gitHubUsers.data.local.UserDatabase
import com.ahrokholska.gitHubUsers.data.local.users.UserEntity
import com.ahrokholska.gitHubUsers.domain.mappers.toUserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator @Inject constructor(
    private val userRemote: UserAPI,
    private val userLocal: UserDatabase
) : RemoteMediator<Int, UserEntity>() {
    override suspend fun initialize(): InitializeAction = withContext(Dispatchers.IO) {
        if (userLocal.getUserDao.isEmpty()) InitializeAction.LAUNCH_INITIAL_REFRESH else InitializeAction.SKIP_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult =
        withContext(Dispatchers.IO) {
            try {
                val loadKey = when (loadType) {
                    LoadType.REFRESH -> -1
                    LoadType.PREPEND ->
                        return@withContext MediatorResult.Success(endOfPaginationReached = false)

                    LoadType.APPEND -> {
                        val lastItem = state.lastItemOrNull()
                            ?: return@withContext MediatorResult.Success(
                                endOfPaginationReached = false
                            )
                        lastItem.id
                    }
                }

                val response = userRemote.getUsers(loadKey)

                with(userLocal) {
                    withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            getUserDao.clearAll()
                        }
                        getUserDao.insertAll(response.map {
                            it.toUserEntity()
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

