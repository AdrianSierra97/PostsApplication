package com.example.postsapplication.framework.di

import com.example.postsapplication.framework.UseCases
import dagger.Module
import dagger.Provides
import repository.PostRepository
import usecase.AddPost
import usecase.GetAllPost
import usecase.GetPost
import usecase.RemovePost

@Module
class UseCasesModule {
    @Provides
    fun getUseCases(
        repository: PostRepository
    ) = UseCases(
        AddPost(repository),
        GetAllPost(repository),
        GetPost(repository),
        RemovePost(repository)
    )
}