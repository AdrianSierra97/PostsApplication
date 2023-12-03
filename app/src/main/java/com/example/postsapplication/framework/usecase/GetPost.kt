package com.example.postsapplication.framework.usecase

import com.example.postsapplication.framework.RemotePostRepository

class GetPost(private val remotePostRepository: RemotePostRepository){
    suspend operator fun invoke(id: Int) = remotePostRepository.getPost(id)
}