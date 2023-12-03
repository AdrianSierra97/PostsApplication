package com.example.postsapplication.framework.di

import android.app.Application
import com.example.postsapplication.framework.LocalPostRepositoryImpl
import com.example.postsapplication.framework.RemotePostRepository
import com.example.postsapplication.framework.network.RemotePostRepositoryImpl
import dagger.Module
import dagger.Provides
import repository.LocalPostRepository

@Module
class RepositoryModule {

    @Provides
    fun providePostRepository(
        app: Application,
    ) = LocalPostRepository(LocalPostRepositoryImpl(app))

    @Provides
    fun provideRemotePostRepository(
    ) = RemotePostRepository(RemotePostRepositoryImpl())
}