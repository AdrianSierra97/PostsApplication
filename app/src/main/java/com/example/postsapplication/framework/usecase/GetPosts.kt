package com.example.postsapplication.framework.usecase

import com.example.postsapplication.framework.RemotePostRepository

class GetPosts(private val remotePostRepository: RemotePostRepository){
    suspend operator fun invoke() = remotePostRepository.getPosts()
}