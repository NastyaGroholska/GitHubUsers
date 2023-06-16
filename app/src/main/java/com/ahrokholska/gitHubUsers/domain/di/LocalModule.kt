package com.ahrokholska.gitHubUsers.domain.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.ahrokholska.gitHubUsers.data.local.UserDatabase
import com.ahrokholska.gitHubUsers.data.local.users.UserEntity
import com.ahrokholska.gitHubUsers.data.remote.users.UserRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext appContext: Context): UserDatabase =
        Room.databaseBuilder(
            appContext,
            UserDatabase::class.java,
            "github_users.db"
        ).build()

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideUserPager(
        mediator: UserRemoteMediator, database: UserDatabase
    ): Pager<Int, UserEntity> = Pager(
        config = PagingConfig(
            pageSize = 30,
            prefetchDistance = 0,
            initialLoadSize = 30,
            enablePlaceholders = true
        ),
        remoteMediator = mediator,
        pagingSourceFactory = {
            database.getUserDao.pagingSource()
        }
    )
}