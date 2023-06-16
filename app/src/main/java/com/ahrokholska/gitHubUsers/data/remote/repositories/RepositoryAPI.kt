package com.ahrokholska.gitHubUsers.data.remote.repositories

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface RepositoryAPI {
    @GET("/users/{username}/repos")
    @Headers("Accept: application/vnd.github+json")
    suspend fun getRepositoriesForUser(
        @Path("username") username: String,
        @Query("page") page: Int, @Query("sort") sort: String = "id"
    ): List<RepositoryDTO>
}