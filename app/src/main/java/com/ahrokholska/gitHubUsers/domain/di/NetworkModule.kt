package com.ahrokholska.gitHubUsers.domain.di

import com.ahrokholska.gitHubUsers.data.remote.BASE_URL
import com.ahrokholska.gitHubUsers.data.remote.repositories.RepositoryAPI
import com.ahrokholska.gitHubUsers.data.remote.users.UserAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .client(OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
        }.build())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    @Provides
    @Singleton
    fun provideUserAPI(retrofit: Retrofit): UserAPI =
        retrofit.create(UserAPI::class.java)

    @Provides
    @Singleton
    fun provideRepositoryAPI(retrofit: Retrofit): RepositoryAPI =
        retrofit.create(RepositoryAPI::class.java)
}