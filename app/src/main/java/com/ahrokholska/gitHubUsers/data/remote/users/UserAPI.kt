package com.ahrokholska.gitHubUsers.data.remote.users

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UserAPI {
    @GET("/users")
    @Headers("Accept: application/vnd.github+json")
    suspend fun getUsers(@Query("since") lastUserId: Int): List<UserDTO>
}