package com.example.postsapplication.framework.di

import com.example.core.UseCases
import dagger.Module
import dagger.Provides
import repository.LocalPostRepository
import usecase.AddPost
import usecase.GetComments
import usecase.GetPosts
import usecase.GetPost
import usecase.InsertComments
import usecase.InsertPosts

@Module
class UseCasesModule {
    @Provides
    fun getUseCases(
        repository: LocalPostRepository
    ) = UseCases(
        AddPost(repository),
        GetPosts(repository),
        GetPost(repository),
        InsertPosts(repository),
        InsertComments(repository),
        GetComments(repository)
    )
}