package com.example.postsapplication.framework.di

import com.example.postsapplication.framework.RemotePostRepository
import com.example.postsapplication.framework.RemoteUseCases
import com.example.postsapplication.framework.usecase.AddPost
import com.example.postsapplication.framework.usecase.GetComments
import com.example.postsapplication.framework.usecase.GetPost
import com.example.postsapplication.framework.usecase.GetPosts
import dagger.Module
import dagger.Provides


@Module
class RemoteUseCasesModule {

    @Provides
    fun getRemoteUseCases(
        repository: RemotePostRepository
    ) = RemoteUseCases(
        AddPost(repository),
        GetPosts(repository),
        GetPost(repository),
        GetComments(repository)
    )
}