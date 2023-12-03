package com.example.postsapplication.framework.usecase

import com.example.postsapplication.framework.RemotePostRepository
import data.Post

class AddPost(private val remotePostRepository: RemotePostRepository) {
    suspend operator fun invoke(post: Post) = remotePostRepository.addPost(post)
}