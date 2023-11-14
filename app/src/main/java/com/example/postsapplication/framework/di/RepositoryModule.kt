package com.example.postsapplication.framework.di

import android.app.Application
import com.example.postsapplication.framework.RoomPostDataSource
import dagger.Module
import dagger.Provides
import repository.PostRepository

@Module
class RepositoryModule {

    @Provides
    fun providePostRepository(
        app: Application,
    ) = PostRepository(RoomPostDataSource(app))
}