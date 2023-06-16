package com.ahrokholska.gitHubUsers.data.local.repositories

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repositories: List<RepositoryEntity>)

    @Query("SELECT * FROM repositories WHERE owner=:userName")
    fun pagingSource(userName: String): PagingSource<Int, RepositoryEntity>

    @Query("DELETE FROM repositories WHERE owner=:userName")
    suspend fun clearAll(userName: String)

    @Query("SELECT (SELECT COUNT(*) FROM repositories WHERE owner=:userName) == 0")
    fun isEmpty(userName: String): Boolean

    @Query("SELECT MAX(page) FROM  repositories WHERE owner=:userName")
    fun lastPage(userName: String): Int
}